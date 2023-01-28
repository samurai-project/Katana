package dev.nmgalo.profile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation


fun NavGraphBuilder.profileGraph() {
    navigation(route = "profile", startDestination = "me") {
        composable("me") {
            PersonalProfileScreen()
        }
    }
}
