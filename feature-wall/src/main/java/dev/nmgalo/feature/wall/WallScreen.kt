package dev.nmgalo.feature.wall

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import dev.nmgalo.core.model.wall.Wall
import dev.nmgalo.core.ui.MobileFullPreview

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
        item {
            CreatePost()
        }
        items(wall) { wallItem ->
            WallItem(item = wallItem)
        }
    }
}

@Composable
fun CreatePost() {
    val inputData = remember { mutableStateOf("What's on your mind?") }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(all = 10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color.Black),
        shape = RoundedCornerShape(
            topStartPercent = 0,
            topEndPercent = 40,
            bottomEndPercent = 0,
            bottomStartPercent = 0
        )
    ) {
        BasicTextField(
            value = inputData.value,
            onValueChange = { inputData.value = it },
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize(),
        )
    }
}

@Composable
fun WallItem(modifier: Modifier = Modifier, item: Wall) {
    Column(
        modifier = modifier
            .padding(all = 10.dp)
            .fillMaxWidth(),
    ) {
        AsyncImage(
            model = "https://loremflickr.com/536/319",
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
        }
    }
}


@MobileFullPreview
@Composable
fun Preview() {
    CreatePost()
}

