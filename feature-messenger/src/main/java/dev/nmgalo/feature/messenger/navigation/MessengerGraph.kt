package dev.nmgalo.feature.messenger.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import dev.nmgalo.feature.messenger.ChatListScreen
import dev.nmgalo.feature.messenger.ChatScreen


fun NavGraphBuilder.messengerGraph(navController: NavController) {
    navigation(startDestination = "wall", route = "wall") {
        composable("wall") {
            ChatListScreen(navController = navController)
        }
        composable(
            route = "chat/{chatId}",
            arguments = listOf(navArgument("chatId") { type = NavType.LongType })
        ) {
            ChatScreen(navController = navController)
        }
    }
}

