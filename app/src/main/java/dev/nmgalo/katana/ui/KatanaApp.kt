package dev.nmgalo.katana.ui

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dev.nmgalo.feature.messenger.ChatListScreen
import dev.nmgalo.feature.messenger.ChatScreen
import dev.nmgalo.feature.wall.WallScreen
import dev.nmgalo.katana.navigation.KatanaRoutes
import dev.nmgalo.katana.ui.theme.KatanaTheme
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KatanaApp() {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: KatanaRoutes.WALL
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    KatanaTheme {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                AppDrawer(
                    currentRoute = currentRoute,
                    navigateTo = { route -> navController.navigate(route) },
                    closeDrawer = { coroutineScope.launch { drawerState.close() } },
                    modifier = Modifier
                        .statusBarsPadding()
                        .navigationBarsPadding()
                )
            }
        ) {
            NavHost(navController = navController, startDestination = KatanaRoutes.WALL) {
                composable(KatanaRoutes.WALL) {
                    WallScreen(navController = navController) {
                        coroutineScope.launch { drawerState.open() }
                    }
                }
                composable(KatanaRoutes.MESSENGER) {
                    ChatListScreen(navController = navController)
                }
                composable(
                    route = KatanaRoutes.CHAT,
                    arguments = listOf(navArgument("userId") { type = NavType.LongType })
                ) {
                    ChatScreen(navController = navController)
                }
            }
        }
    }
}
