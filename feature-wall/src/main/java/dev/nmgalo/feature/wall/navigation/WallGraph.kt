package dev.nmgalo.feature.wall.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.nmgalo.feature.wall.WallScreen

fun NavGraphBuilder.wallGraph() {
    navigation(startDestination = "home", route = "wall") {
        composable("home") {
            WallScreen()
        }
    }
}
