package itm.pbl.cleancity.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.size.Size
import itm.pbl.cleancity.presentation.HeroStorage
import itm.pbl.cleancity.R
import itm.pbl.cleancity.ui.theme.CardViewColor
import itm.pbl.cleancity.ui.theme.GreenLightWala
import itm.pbl.cleancity.ui.theme.backgroundColormain

@Composable
fun VilenessScreen() {
    Surface (
        modifier = Modifier.padding(top = 60.dp),
        color = backgroundColormain
    ){
        ListHeroImages(users = dummyData())
    }
}


@Composable
fun HeroSectionCard (data: HeroStorage) {
    Card (
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = CardViewColor
        ),
        shape = RoundedCornerShape(CornerSize((4.dp))),
        elevation = CardDefaults.cardElevation(2.dp)

    ) {
        Column (modifier = Modifier.padding(5.dp)){
            Image(painter = painterResource(R.drawable.heroimage), contentDescription = "null" ,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(250.dp)
                    .align(Alignment.CenterHorizontally)
                    .clip(RoundedCornerShape(CornerSize(1.dp))),

                )
            Text(text = data.discription , modifier = Modifier.padding(horizontal = 35.dp))
            Text(text = data.location, Modifier.padding(horizontal = 35.dp))
        }
    }
}


@Composable
fun ListHeroImages (users: List<itm.pbl.cleancity.presentation.HeroStorage>) {
    LazyColumn {
        items(users){user ->
            HeroSectionCard(user)
        }
    }
}

