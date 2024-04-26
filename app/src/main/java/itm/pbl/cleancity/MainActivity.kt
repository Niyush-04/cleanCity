package itm.pbl.cleancity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import itm.pbl.cleancity.presentation.ListHeroImages
import itm.pbl.cleancity.presentation.dummyData
import itm.pbl.cleancity.ui.theme.CleanCityTheme
import itm.pbl.cleancity.ui.theme.GreenLightWala
import itm.pbl.cleancity.ui.theme.backgroundColormain

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CleanCityTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    Scaffold(
                        topBar = {
                            TopAppBar()
                        },
                        bottomBar = {
                            BottomBar(navController = navController)
                        }
                    ) {
                        CleanCityNavigationGraph(navController = navController)
                    }
                }

            }
        }
    }
}

@Composable
fun BottomBar(navController: NavController) {
    var selectedIndex by remember {
        mutableIntStateOf(0)
    }
    val screens = listOf(
        Screens.vileness,
        Screens.morality,
        Screens.polls,
        Screens.setting
    )

    NavigationBar(
        containerColor = GreenLightWala
    ) {

        screens.forEachIndexed { index, screens ->
            NavigationBarItem(

                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = GreenLightWala

                ),
                selected = selectedIndex == index,
                onClick = {
                    navController.navigate(screens.route)
                    selectedIndex = index
                },
                icon = {
                    Icon(
                        imageVector = screens.icon,
                        contentDescription = "bottomBar Icon"
                    )
                },
                label = {
                    Text(text = screens.title)
                },
                alwaysShowLabel = true
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar() {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(GreenLightWala),
        title = {
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = "CleanCity App"
            )
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.Rounded.Menu,
                contentDescription = null
            )
        },
        actions = {
            IconButton(onClick = {}) {
                Image(
                    painter = painterResource(R.drawable.autumn),
                    contentDescription = "",
                    Modifier.size(30.dp)
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    Icons.Filled.Notifications,
                    contentDescription = "notification",
                    Modifier.size(30.dp)
                )
            }
            IconButton(onClick = {}) {
                Image(
                    painter = painterResource(R.drawable.profile),
                    contentDescription = "profile",
                    Modifier.size(30.dp)
                )
            }
        })
}

