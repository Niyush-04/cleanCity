package itm.pbl.cleancity.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import itm.pbl.cleancity.R
import itm.pbl.cleancity.ui.theme.GreenLightWala
import itm.pbl.cleancity.ui.theme.backgroundColormain


@Composable
fun MoralityScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColormain)
            .padding(top = 70.dp, start = 15.dp, bottom = 70.dp, end = 15.dp)
            .verticalScroll(state = rememberScrollState()),
    ) {
        Mycard3(R.drawable.image1,location = "gwalior")
        Mycard3(R.drawable.image1,location = "gwalior")
        Mycard3(R.drawable.image1,location = "gwalior")
        Mycard3(R.drawable.image1,location = "gwalior")
        Mycard3(R.drawable.image1,location = "gwalior")
    }
}

@Composable
fun Mycard3(image: Int, location: String) {
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
            painter = painterResource(id = image),
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
                imageVector = Icons.Rounded.MyLocation, contentDescription = "location icon"
            )
            Text(text = location, modifier = Modifier.padding(start = 10.dp))
        }
    }

}
