package dev.nmgalo.feature.messenger.chat

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.NearMe
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.nmgalo.core.ui.OnNavigation
import dev.nmgalo.feature.messenger.model.Message
import dev.nmgalo.feature.messenger.model.User

@Composable
fun ChatScreen(
    onCall: OnNavigation,
    viewModel: ChatViewModel = hiltViewModel(),
) {
    val conversationState = viewModel.chatState.collectAsState()

    when (val state = conversationState.value) {
        ChatState.Error -> TODO()
        ChatState.Loading -> Text("Cool loader.")
        is ChatState.Success -> MessageList(
            conversation = state.conversation,
            onSend = {
                viewModel.sendMessage(it)
                conversationState.value
            },
            onCall = { onCall.invoke("group-call/2") },
        )
    }
}

@Composable
fun MessageList(conversation: List<Message>, onSend: (String) -> Unit, onCall: () -> Unit) {
    val context = LocalContext.current
    Column {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(weight = 1f),
            reverseLayout = true
        ) {
            items(conversation.size) { index ->
                ConversationMessageItem(message = conversation[index], onLongPress = {
                    Toast.makeText(context, "OnLongPress", Toast.LENGTH_SHORT).show()
                })
            }
        }
        Box {
            MessageBar(
                onSend = onSend,
                onCall = onCall
            )
        }
    }
}

@Composable
fun ConversationMessageItem(
    message: Message,
    onLongPress: () -> Unit,
    modifier: Modifier = Modifier
) {

    val alignment = if (message.isMe) Alignment.End else Alignment.Start

    // Pair<Background, TextColor>
    val color: Pair<Color, Color> = with(MaterialTheme.colorScheme) {
        if (message.isMe) primary to onPrimary else surfaceVariant to onSurfaceVariant
    }

    Column(
        modifier = modifier
            .padding(horizontal = 20.dp, vertical = 5.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = message.user.name,
            style = MaterialTheme.typography.labelSmall,
            modifier = modifier.align(alignment)
        )
        Column(
            modifier = modifier
                .align(alignment)
                .padding(top = 5.dp)
                .fillMaxWidth(fraction = 0.80f)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onLongPress = {
                            onLongPress()
                        }
                    )
                }
        ) {
            Box(
                modifier = modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(color = color.first)
                    .align(alignment)
            ) {
                Text(
                    text = message.message,
                    modifier = modifier.padding(vertical = 10.dp, horizontal = 15.dp),
                    color = color.second
                )
            }
        }
    }
}

@Composable
fun MessageBar(
    modifier: Modifier = Modifier,
    onSend: (String) -> Unit,
    onCall: () -> Unit
) {
    val text = remember { mutableStateOf("") }
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { }) {
            Icon(
                Icons.Outlined.NearMe, "Send live location",
            )
        }
        IconButton(onClick = { }) {
            Icon(
                Icons.Outlined.PhotoCamera, "Send Image",
            )
        }

        IconButton(onClick = onCall::invoke) {
            Icon(
                Icons.Outlined.Call, "Send Image",
            )
        }

        Box(
            modifier = modifier
                .weight(weight = 1f)
                .padding(5.dp)
                .clip(RoundedCornerShape(35.dp))
                .background(MaterialTheme.colorScheme.surfaceVariant)
        ) {
            BasicTextField(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 20.dp),
                value = text.value,
                maxLines = 3,
                onValueChange = { text.value = it },
                textStyle = TextStyle(color = MaterialTheme.colorScheme.onSurfaceVariant)
            )
        }

        IconButton(onClick = { onSend(text.value) }) {
            Icon(
                Icons.Outlined.Send, "Send",
                modifier = modifier.width(width = 40.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConversationListPreview() {
    val user = User(1L, "nmgalo")
    val mockMessage = listOf(
        Message(1, "Hello", user, true),
        Message(1, "Hello again", user, false),
    )
    MessageList(mockMessage, onSend = {}, onCall = {})
}
