package dev.nmgalo.feature.wall

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WallScreen(
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
        LazyColumn(contentPadding = padding, modifier = Modifier.fillMaxSize()) {
            when (val wallState = wallState.value) {
                WallUiState.Loading -> {
                    item {
                        Text("Loading")
                    }
                }
                is WallUiState.Success -> {
                    wallState.wall.forEach {
                        item {
                            Text(it.title)
                        }
                    }
                }
            }
        }
    }
}
