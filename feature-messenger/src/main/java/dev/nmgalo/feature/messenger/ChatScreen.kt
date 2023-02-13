package dev.nmgalo.feature.messenger

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.nmgalo.core.ui.MobileFullPreview
import dev.nmgalo.feature.messenger.model.Message


@Composable
fun ChatScreen(
    viewModel: ChatViewModel = hiltViewModel()
) {
    val conversationState = viewModel.conversationState.collectAsState()

    when (val state = conversationState.value) {
        ConversationState.Error -> TODO()
        ConversationState.Loading -> Text("Cool loader.")
        is ConversationState.Success -> MessageList(state.conversation)
    }
}

@Composable
fun MessageList(conversation: List<Message>) {
    LazyColumn(modifier = Modifier.fillMaxSize(), reverseLayout = true) {
        items(conversation.size) { index ->
            ConversationMessageItem(conversation[index])
        }
    }
}

@Composable
fun ConversationMessageItem(message: Message, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(horizontal = 20.dp, vertical = 5.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = modifier
                .align(if (message.isMe) Alignment.End else Alignment.Start)
                .clip(RoundedCornerShape(20.dp))
                .fillMaxWidth(fraction = 0.90f)
                .background(color = if (message.isMe) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant)
        ) {
            SelectionContainer {
                Text(
                    text = message.message,
                    modifier = modifier.padding(10.dp),
                    color = if (message.isMe) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@MobileFullPreview
@Composable
fun Preview() {
    ChatScreen()
}
