package itm.pbl.cleancity.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MyLocation
import androidx.compose.material.icons.rounded.ThumbDown
import androidx.compose.material.icons.rounded.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import itm.pbl.cleancity.ui.theme.GreenLightWala
import itm.pbl.cleancity.ui.theme.backgroundColormain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Composable
fun PollsScreen() {
    Surface(
        modifier = Modifier.padding(top = 60.dp, bottom = 50.dp),
        color = backgroundColormain
    ) {
        MyCards2()
    }
}


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MyCards2() {
    val documentKeysState = remember { mutableStateOf<List<String>>(emptyList()) }

    GlobalScope.launch(Dispatchers.IO) {
        val db = FirebaseFirestore.getInstance()
        val collection = db.collection("vileness")
        val querySnapshot = collection.get().await()

        val documentKeys = querySnapshot.documents.map { it.id }
        documentKeysState.value = documentKeys
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(state = rememberScrollState()),
    ) {
        documentKeysState.value.forEach { key ->
            CardItem1(key = key)
        }
    }

}


@Composable
fun CardItem1(key: String) {
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
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(bottom = 16.dp),
        colors = CardDefaults.cardColors(GreenLightWala.copy(0.3f))

    ) {
        Image(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 10.dp)
                .clip(RoundedCornerShape(10.dp)),
            painter = rememberAsyncImagePainter(model = imageUrl),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, bottom = 10.dp)
        ) {
            Icon(
                modifier = Modifier.alpha(0.5f),
                imageVector = Icons.Rounded.MyLocation, contentDescription = "location icon")
            Text(text = location, modifier = Modifier.padding(start = 10.dp))
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(bottom = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                LinearProgressIndicator(
                    progress = 0.7f,
                    color = GreenLightWala,
                )
                Icon(
                    modifier = Modifier.alpha(0.5f),
                    imageVector = Icons.Rounded.ThumbUp, contentDescription = null)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                LinearProgressIndicator(
                    progress = 0.7f,
                    color = GreenLightWala,
                )
                Icon(
                    modifier = Modifier.alpha(0.5f),
                    imageVector = Icons.Rounded.ThumbDown, contentDescription = null)
            }
        }
    }
}


