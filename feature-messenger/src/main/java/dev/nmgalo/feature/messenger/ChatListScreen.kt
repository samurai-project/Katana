package dev.nmgalo.feature.messenger

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import dev.nmgalo.core.ui.MobileFullPreview
import dev.nmgalo.feature.messenger.model.Chat

typealias OnItemClick = (route: String) -> Unit

@Suppress("LongMethod")
@Composable
fun ChatListScreen(
    onItemClick: OnItemClick,
    modifier: Modifier = Modifier,
    viewModel: ChatListViewModel = hiltViewModel()
) {

    val state = viewModel.chatListState.collectAsState()

    when (val chatState = state.value) {
        ChatListState.Error,
        ChatListState.Loading -> Unit
        ChatListState.Empty -> Box(modifier = modifier.fillMaxSize()) {
            Text(
                stringResource(R.string.no_chat_message),
                modifier = modifier.align(Alignment.Center)
            )
        }
        is ChatListState.Success -> {
            LazyColumn(modifier = modifier.fillMaxSize()) {
                items(count = chatState.data.size) { index ->
                    ConversationItem(chat = chatState.data[index], onItemClick::invoke)
                }
            }
        }
    }
}

@Composable
fun ConversationItem(chat: Chat, onItemClick: OnItemClick, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(horizontal = 10.dp, vertical = 8.dp)
            .fillMaxSize()
            .clickable {
                onItemClick("chat/${chat.id}")
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box {
            AsyncImage(
                model = chat.userProfilePicture,
                contentDescription = "Contact profile picture",
                modifier = modifier
                    .padding(all = 5.dp)
                    .size(40.dp)
                    .align(Alignment.Center)
                    .clip(CircleShape)
            )
            Canvas(modifier = modifier
                .padding(all = 5.dp)
                .size(12.dp)
                .align(Alignment.BottomEnd), onDraw = {
                drawCircle(color = Color.Green)
            })
        }

        Spacer(modifier = modifier.width(8.dp))

        UserIdentifierColumn(chat)
    }
}

@Composable
fun UserIdentifierColumn(chat: Chat) {
    Column {
        Text(text = chat.userName, style = MaterialTheme.typography.titleSmall)
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = chat.lastMessage,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@MobileFullPreview
@Composable
fun PreviewChatList() {
    ConversationItem(Chat(
        id = 1,
        userName = "Nika Mgalo",
        userProfilePicture = "https://",
        lastMessage = "nick"
    ), onItemClick = {})
}
