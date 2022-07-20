package dev.nmgalo.katana.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.qarva.feature.messenger.ChatScreen
import dev.nmgalo.feature.wall.WallScreen
import dev.nmgalo.katana.ui.theme.KatanaTheme


@Composable
fun KatanaApp() {
    val navController = rememberNavController()
    KatanaTheme {
        NavHost(navController = navController, startDestination = "wall") {
            composable("wall") { WallScreen() }
            composable("messenger") { ChatScreen() }
        }
    }
}
