package dev.nmgalo.feature.messenger.chat


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun UserInputPreview() {
    MessageBarNew(onMessageSent = {})
}

@Composable
fun MessageBarNew(
    modifier: Modifier = Modifier,
    onMessageSent: (String) -> Unit,
    resetScroll: () -> Unit = {},
) {
    val text = remember { mutableStateOf("") }
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { }) {
            Icon(
                Icons.Outlined.PhotoCamera, "Send Image",
            )
        }

        IconButton(onClick = {}) {
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

        IconButton(onClick = { onMessageSent(text.value) }) {
            Icon(
                Icons.Outlined.Send, "Send",
                modifier = modifier.width(width = 40.dp)
            )
        }
    }
}

