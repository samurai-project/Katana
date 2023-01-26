package dev.nmgalo.katana.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sms
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dev.nmgalo.feature.messenger.navigation.messengerGraph
import dev.nmgalo.feature.wall.navigation.wallGraph
import dev.nmgalo.katana.ui.theme.KatanaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KatanaApp() {

    val navController = rememberNavController()

    KatanaTheme {
        Scaffold(
            topBar = {
                SmallTopAppBar(title = { Text("Wall") }, actions = {
                    IconButton(onClick = {
                        navController.navigate("messenger")
                    }) {
                        BadgedBox(badge = {
                            Badge {
                                Text(text = "3", color = Color.White)
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Sms, contentDescription = "Chat"
                            )
                        }
                    }
                })
            },
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground,
        ) { padding ->
            Row(modifier = Modifier.padding(padding)) {
                NavHost(navController = navController, startDestination = "wall") {
                    wallGraph()
                    messengerGraph()
                }
            }
        }
    }
}
