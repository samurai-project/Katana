package dev.nmgalo.settings.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import dev.nmgalo.settings.GeneralSettingsScreen

fun NavGraphBuilder.settingsGraph() {
    navigation(route = "settings", startDestination = "general") {
        composable("general") { GeneralSettingsScreen() }
    }
}
