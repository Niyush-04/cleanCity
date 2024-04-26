package itm.pbl.cleancity.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import itm.pbl.cleancity.ui.theme.backgroundColormain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Composable
fun VilenessScreen() {
    Surface (
        modifier = Modifier.padding(top = 60.dp, bottom = 50.dp),
        color = backgroundColormain
    ){
        MyCards()
    }
}



@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MyCards () {
    val documentKeysState = remember { mutableStateOf<List<String>>(emptyList()) }

    GlobalScope.launch(Dispatchers.IO) {
        val db = FirebaseFirestore.getInstance()
        val collection = db.collection("vileness")
        val querySnapshot = collection.get().await()

        val documentKeys = querySnapshot.documents.map { it.id }
        documentKeysState.value = documentKeys
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
        .verticalScroll(state = rememberScrollState())) {
        documentKeysState.value.forEach { key ->
            Posts(key = key)
        }
    }
}

@Composable
fun Posts(key: String) {
    val db = Firebase.firestore
    val ref = db.collection("vileness").document(key)

    var location by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf("") }

    LaunchedEffect(key) {
        ref.get().addOnSuccessListener { document ->
            if (document != null) {
                location = document.data?.get("location").toString()
                imageUrl = document.data?.get("imageSrc").toString()
            }
        }
    }

    Card(
        modifier = Modifier.fillMaxWidth().background(Color.Transparent).padding(bottom = 16.dp)

    ) {
        Image(
            modifier = Modifier
                .height(250.dp)
                .fillMaxWidth()
                .padding(20.dp)
                .clip(RoundedCornerShape(10.dp)),
            painter = rememberAsyncImagePainter(model = imageUrl),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Text(text = location, modifier = Modifier.padding(start = 10.dp))
    }
}


