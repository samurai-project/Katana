package dev.nmgalo.katana.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.nmgalo.katana.ui.navigation.Screen

@Composable
fun rememberKatanaAppState(
    navController: NavHostController = rememberNavController()
): KatanaAppState {
    return remember(navController) {
        KatanaAppState(navController)
    }
}


@Stable
class KatanaAppState(
    val navController: NavHostController
) {

    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: Screen?
        @Composable get() = when (currentDestination?.route) {
            "home" -> Screen.Wall
            "me" -> Screen.Profile
            "general" -> Screen.Settings
            else -> null
        }

    fun navigate(destination: String) {
        navController.navigate(destination)
    }

    fun topLevelNavigation(destination: String) {
        navController.navigate(destination) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}
