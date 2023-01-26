package dev.nmgalo.feature.messenger.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import dev.nmgalo.feature.messenger.ChatListScreen
import dev.nmgalo.feature.messenger.ChatScreen
import dev.nmgalo.feature.messenger.ChatSettingsScreen
import dev.nmgalo.feature.messenger.p2p.GroupCallScreen


fun NavGraphBuilder.messengerGraph() {
    navigation(startDestination = "list", route = "messenger") {
        composable("list") {
            ChatListScreen()
        }
        composable("settings") {
            ChatSettingsScreen()
        }
        composable(
            route = "chat/{chatId}",
            arguments = listOf(navArgument("chatId") { type = NavType.LongType })
        ) {
            ChatScreen()
        }
        composable("group-call/{peerId}") {
            GroupCallScreen()
        }
    }
}

