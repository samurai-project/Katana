package dev.nmgalo.feature.wall

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material3.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
    ) {
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
            .height(100.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
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
            .padding(vertical = 10.dp)
            .border(1.dp, MaterialTheme.colorScheme.outline)
            .fillMaxWidth(),
    ) {
        WallItemHeader()
        AsyncImage(
            model = "https://loremflickr.com/536/319",
            contentScale = ContentScale.Crop,
            contentDescription = item.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )
        WallItemActions()
    }
}

@Composable
fun WallItemHeader(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Row {
                AsyncImage(
                    model = "https://loremflickr.com/536/536",
                    contentDescription = "User profile image",
                    modifier = modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .border(.5.dp, Color.Black)
                )
            }
            Row {
                Icon(Icons.Filled.MoreHoriz, "More", modifier = modifier.clickable { })
            }
        }
        Row(modifier = modifier.padding(top = 32.dp, bottom = 14.dp)) {
            Text("A great day when you are confident in yourself!")
        }
    }
}

@Composable
fun WallItemActions() {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row {
            Icon(
                painterResource(R.drawable.ic_heart),
                "Favorite",
                modifier = Modifier.clickable {
                    TODO("Not yet implemented")
                }
            )
        }
        Row {
            Text(
                text = stringResource(R.string.comments_count, 2),
                style = MaterialTheme.typography.labelMedium,
            )
        }
    }
}
