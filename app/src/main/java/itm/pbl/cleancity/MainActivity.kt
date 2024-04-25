package itm.pbl.cleancity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import itm.pbl.cleancity.ui.theme.CleanCityTheme

class MainActivity : ComponentActivity() {
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

    NavigationBar {
        screens.forEachIndexed { index, screens ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = {
                    navController.navigate(screens.route)
                    selectedIndex = index
                },
                icon = {
                    Icon(imageVector = screens.icon,
                        contentDescription = "bottomBar Icon")
                       },
                label = {
                    Text(text = screens.title)
                },
                alwaysShowLabel = true
            )
        }
    }
}
