package dev.nmgalo.feature.messenger.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import dev.nmgalo.feature.messenger.ChatSettingsScreen
import dev.nmgalo.feature.messenger.chat.ChatScreen
import dev.nmgalo.feature.messenger.conversations.ChatListScreen
import dev.nmgalo.feature.messenger.p2p.GroupCallScreen


fun NavGraphBuilder.messengerGraph(onNavigation: (route: String) -> Unit) {
    navigation(startDestination = "list", route = "messenger") {
        composable("list") {
            ChatListScreen(onNavigation::invoke)
        }
        composable("settings") {
            ChatSettingsScreen()
        }
        composable(
            route = "chat/{chatId}",
            arguments = listOf(navArgument("chatId") { type = NavType.LongType })
        ) {
            ChatScreen(onNavigation::invoke)
        }
        composable("group-call/{peerId}") {
            GroupCallScreen()
        }
    }
}

