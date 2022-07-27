package dev.nmgalo.feature.wall.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.nmgalo.feature.wall.WallScreen

fun NavGraphBuilder.wallGraph(navController: NavController, openDrawer: () -> Unit) {
    navigation(startDestination = "wall", route = "wall") {
        composable("wall") {
            WallScreen(navController = navController, openDrawer = openDrawer)
        }
    }
}
