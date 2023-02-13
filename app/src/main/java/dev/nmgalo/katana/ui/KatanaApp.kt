package dev.nmgalo.katana.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.nmgalo.feature.messenger.navigation.messengerGraph
import dev.nmgalo.feature.wall.navigation.wallGraph
import dev.nmgalo.katana.R
import dev.nmgalo.katana.ui.navigation.Screen
import dev.nmgalo.katana.ui.theme.KatanaBackground
import dev.nmgalo.profile.navigation.profileGraph
import dev.nmgalo.settings.navigation.settingsGraph

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KatanaApp() {

    val navController = rememberNavController()

    KatanaBackground {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text(stringResource(id = R.string.app_name)) }, actions = {
                    IconButton(onClick = {
                        navController.navigate("messenger")
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_messenger),
                            contentDescription = "Messenger"
                        )
                    }
                })
            },
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground,
            bottomBar = {
                KatanaBottomNav(navController) { destination ->
                    navController.navigate(destination) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        ) { padding ->
            Row(modifier = Modifier.padding(padding)) {
                NavHost(navController = navController, startDestination = Screen.Wall.route) {
                    wallGraph()
                    messengerGraph { navController.navigate(it) }
                    profileGraph()
                    settingsGraph()
                }
            }
        }
    }
}

@Composable
fun KatanaBottomNav(navController: NavController, onNavigation: (String) -> Unit) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        Screen.values().forEach { screen ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = screen.icon),
                        modifier = Modifier.height(25.dp),
                        contentDescription = screen.name
                    )
                },
                label = { Text(stringResource(id = screen.title)) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    onNavigation(screen.route)
                }
            )
        }
    }
}
