package dev.nmgalo.feature.messenger

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.nmgalo.core.ui.MobileFullPreview

typealias OnItemClick = (route: String) -> Unit

@Suppress("LongMethod")
@Composable
fun ChatListScreen(
    onItemClick: OnItemClick,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(6) {
            ConversationItem(onItemClick::invoke)
        }
    }
}

@Composable
fun ConversationItem(onItemClick: OnItemClick, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(horizontal = 10.dp, vertical = 8.dp)
            .fillMaxSize()
            .clickable {
                val mockChatId = 1L
                onItemClick("chat/$mockChatId")
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box {
            AsyncImage(
                model = "https://loremflickr.com/536/536",
                contentDescription = "Contact profile picture",
                modifier = modifier
                    .padding(all = 5.dp)
                    .size(40.dp)
                    .align(Alignment.Center)
                    .clip(CircleShape)
            )
            Canvas(modifier = modifier
                .size(12.dp)
                .align(Alignment.BottomEnd), onDraw = {
                drawCircle(color = Color.Green)
            })
        }

        Spacer(modifier = modifier.width(8.dp))

        UserIdentifierColumn()
    }
}

@Composable
fun UserIdentifierColumn() {
    Column {
        Text(text = "Jane doe", style = MaterialTheme.typography.titleSmall)
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = "Hello nick, How are you?",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@MobileFullPreview
@Composable
fun PreviewChatList() {
    ChatListScreen(onItemClick = {})
}
