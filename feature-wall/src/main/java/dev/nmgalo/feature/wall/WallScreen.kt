package dev.nmgalo.feature.wall

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sms
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Badge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import dev.nmgalo.core.model.wall.Wall

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WallScreen(
    modifier: Modifier = Modifier,
    viewModel: WallViewModel = hiltViewModel(),
    navController: NavController,
    openDrawer: () -> Unit
) {
    val wallState = viewModel.wallState.collectAsState()
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("Wall") },
                navigationIcon = {
                    IconButton(onClick = { openDrawer() }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Menu"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("messenger")
                    }) {
                        BadgedBox(
                            badge = {
                                Badge {
                                    Text(text = "3", color = Color.White)
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Sms,
                                contentDescription = "Chat"
                            )
                        }
                    }
                }
            )
        },
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onBackground,
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            modifier = modifier.fillMaxSize()
        ) {
            when (val state = wallState.value) {
                WallUiState.Loading -> {
                    item {
                        Text("Loading")
                    }
                }
                is WallUiState.Success -> {
                    items(state.wall) { wallItem ->
                        WallItem(item = wallItem)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WallItem(modifier: Modifier = Modifier, item: Wall) {
    Card(
        modifier = modifier
            .padding(all = 10.dp)
            .fillMaxWidth()
            .clickable { },
    ) {
        AsyncImage(
            model = "https://loremflickr.com/536/${300 + item.id}",
            contentScale = ContentScale.Crop,
            contentDescription = item.title,
            modifier = modifier
                .fillMaxWidth()
                .height(300.dp)
        )
        Row(
            modifier = modifier
                .padding(vertical = 5.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.ThumbUp,
                    contentDescription = "Like",
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.ThumbDown,
                    contentDescription = "Dislike",
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.Comment,
                    contentDescription = "Comment"
                )
            }
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.Share,
                    contentDescription = "Share"
                )
            }
        }
    }
}

