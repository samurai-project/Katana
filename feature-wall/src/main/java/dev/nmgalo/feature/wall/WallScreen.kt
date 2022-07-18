package dev.nmgalo.feature.wall

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import dev.nmgalo.core.model.wall.Wall

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WallScreen(
    modifier: Modifier = Modifier,
    viewModel: WallViewModel = hiltViewModel()
) {
    val wallState = viewModel.wallState.collectAsState()
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("Wall") },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.AccountCircle,
                            contentDescription = "Localized description"
                        )
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

@Composable
fun WallItem(modifier: Modifier = Modifier, item: Wall) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(15.dp),
    ) {
        AsyncImage(
            model = "https://picsum.photos/536/${355 + item.id}",
            contentScale = ContentScale.Crop,
            contentDescription = item.title,
            modifier = modifier
                .fillMaxWidth()
                .height(300.dp)
        )
        Text(text = item.title.take(30))
    }
}

