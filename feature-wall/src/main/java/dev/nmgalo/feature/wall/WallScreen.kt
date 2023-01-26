package dev.nmgalo.feature.wall

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import dev.nmgalo.core.model.wall.Wall

@Composable
fun WallScreen(
    viewModel: WallViewModel = hiltViewModel(),
) {
    val wallState = viewModel.wallState.collectAsState()
    when (val state = wallState.value) {
        WallUiState.Loading -> Loader()
        WallUiState.Error -> Text("An error occurred, changed this error later!")
        is WallUiState.Success -> ChatList(wall = state.wall)
    }
}

@Composable
fun Loader(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ChatList(modifier: Modifier = Modifier, wall: List<Wall>) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(wall) { wallItem ->
            WallItem(item = wallItem)
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

