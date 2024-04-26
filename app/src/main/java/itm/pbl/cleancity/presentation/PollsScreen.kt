package itm.pbl.cleancity.presentation

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import itm.pbl.cleancity.ui.theme.GreenLightWala
import itm.pbl.cleancity.ui.theme.whitecp

@Composable
fun PollsScreen() {

    val context = LocalContext.current

    val storageref = FirebaseStorage.getInstance()
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    var imageUrl by remember { mutableStateOf("") }

    val galleryImage = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let { imageUri =it }
        }
    )
    var location by remember { mutableStateOf("") }




    val db = Firebase.firestore

    val userMap = hashMapOf(
        "imageSrc" to imageUrl,
        "location" to location
    )
    var flag by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        Card(modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(10.dp)
            .clickable {
                galleryImage.launch("image/*")
                flag = true
            },
            ) {

            if(!flag) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Icon(
                        modifier = Modifier.size(100.dp, 100.dp),
                        imageVector = Icons.Rounded.AddCircle, contentDescription = null,
                    )
                    Text(text = "Add Image")

                }
            }


            if (flag)
            {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = rememberAsyncImagePainter(model = imageUri),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
            }

        }
        TextField(
            value = location, onValueChange = { location = it },
            placeholder = { Text(text = "Enter Location")},

            shape = RoundedCornerShape(50),


        )

        Button(onClick = {
            imageUri?.let {
                storageref.getReference("images").child(System.currentTimeMillis().toString())
                    .putFile(it)
                    .addOnSuccessListener { task ->
                        task.metadata!!.reference!!.downloadUrl
                            .addOnSuccessListener {
                                imageUrl = it.toString()
                                Toast.makeText(context,"Image Uploaded Successfully!!",Toast.LENGTH_SHORT).show()
                            }
                    }
            }
        }) {
            Text(text = "Upload")
        }

        Button(onClick = {
            db.collection("vileness").document().set(userMap)
                .addOnSuccessListener {
                    Toast.makeText(context,"Data Updated!!",Toast.LENGTH_SHORT).show()
                }
        }) {
            Text(text = "Upload to Firestore")

        }
    }

}