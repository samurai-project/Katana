package dev.nmgalo.katana.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dev.nmgalo.feature.messenger.navigation.messengerGraph
import dev.nmgalo.feature.wall.navigation.wallGraph
import dev.nmgalo.katana.R
import dev.nmgalo.katana.ui.theme.KatanaBackground
import dev.nmgalo.profile.navigation.profileGraph

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
                            imageVector = Icons.Default.Chat,
                            contentDescription = "Chat"
                        )
                    }
                })
            },
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground,
            bottomBar = {
                KatanaBottomNav { destination ->
                    navController.navigate(destination)
                }
            }
        ) { padding ->
            Row(modifier = Modifier.padding(padding)) {
                NavHost(navController = navController, startDestination = "wall") {
                    wallGraph()
                    messengerGraph { navController.navigate(it) }
                    profileGraph()
                }
            }
        }
    }
}

@Composable
fun KatanaBottomNav(onNavigation: (String) -> Unit) {

    val selectedIndex = remember { mutableStateOf(0) }

    NavigationBar(modifier = Modifier.height(80.dp)) {
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_wall),
                    modifier = Modifier.height(25.dp),
                    contentDescription = ""
                )
            },
            label = { Text(stringResource(id = R.string.wall)) },
            selected = selectedIndex.value == 0,
            onClick = {
                selectedIndex.value = 0
                onNavigation("wall")
            }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_account),
                    modifier = Modifier.height(25.dp),
                    contentDescription = ""
                )
            },
            label = { Text(stringResource(id = R.string.account)) },
            selected = selectedIndex.value == 1,
            onClick = {
                selectedIndex.value = 1
                onNavigation("profile")
            }
        )
    }
}

@Preview
@Composable
fun Preview() {
    KatanaBottomNav {}
}
