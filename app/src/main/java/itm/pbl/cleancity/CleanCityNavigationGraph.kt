package itm.pbl.cleancity

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import itm.pbl.cleancity.presentation.MoralityScreen
import itm.pbl.cleancity.presentation.PollsScreen
import itm.pbl.cleancity.presentation.SettingScreen
import itm.pbl.cleancity.presentation.VilenessScreen


@Composable
fun CleanCityNavigationGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screens.vileness.route
    ) {
        composable(route = Screens.vileness.route) {
            VilenessScreen()
        }
        composable(route = Screens.morality.route) {
            MoralityScreen()
        }
        composable(route = Screens.polls.route) {
            PollsScreen()
        }
        composable(route = Screens.setting.route) {
            SettingScreen()
        }
    }
}


sealed class Screens(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object vileness : Screens(
        route = "vileness",
        title = "Vileness",
        icon = Icons.Default.Person
    )
    data object morality : Screens(
        route = "morality",
        title = "Morality",
        icon = Icons.Default.Face
    )
    data object polls : Screens(
        route = "polls",
        title = "Polls",
        icon = Icons.Default.ThumbUp
    )
    data object setting : Screens(
        route = "setting",
        title = "Setting",
        icon = Icons.Default.Settings
    )
}