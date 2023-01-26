package dev.nmgalo.feature.messenger

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.nmgalo.core.ui.DevicePreviews


@Composable
fun ChatScreen(modifier: Modifier = Modifier) {
    Column {
        LazyColumn(modifier = modifier.fillMaxSize()) {
//            ConversationsList()
        }
    }
}

@DevicePreviews
@Composable
fun Preview() {
    ChatScreen()
}
