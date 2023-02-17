package dev.nmgalo.feature.messenger

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
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
        is ConversationState.Success -> MessageList(state.conversation) {
            viewModel.sendMessage(it)
        }
    }
}

@Composable
fun MessageList(conversation: List<Message>, onSend: (String) -> Unit) {
    val context = LocalContext.current
    Column {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(weight = 1f), reverseLayout = true
        ) {
            items(conversation.size) { index ->
                ConversationMessageItem(conversation[index], onLongPress = {
                    Toast.makeText(context, "OnLongPress", Toast.LENGTH_SHORT).show()
                })
            }
        }
        Box(modifier = Modifier.height(70.dp)) {
            MessageBar { onSend(it) }
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
    onSend: (String) -> Unit
) {
    val text = remember { mutableStateOf("") }
    Row(
        modifier = modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center
    ) {
        BasicTextField(
            modifier = modifier
                .weight(weight = 1f)
                .fillMaxHeight()
                .padding(10.dp),
            value = text.value,
            maxLines = 3,
            onValueChange = { text.value = it })

        IconButton(modifier = modifier.fillMaxHeight(), onClick = {
            onSend(text.value)
        }) {
            Icon(
                Icons.Outlined.Send, "Send",
                modifier = modifier.width(width = 40.dp)
            )
        }
    }
}

@MobileFullPreview
@Composable
fun Preview() {
    ChatScreen()
}
