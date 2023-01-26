package dev.nmgalo.feature.messenger

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Suppress("LongMethod")
@Composable
fun ChatSettingsScreen(
    modifier: Modifier = Modifier
) {
    val checked = remember { mutableStateOf(false) }

    Column {
        Column(modifier = modifier.padding(all = 10.dp)) {
            Row {
                Text(text = "Header", style = MaterialTheme.typography.headlineMedium)
                Spacer(Modifier.height(20.dp))
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Show status",
                    modifier = modifier.padding(top = 10.dp),
                    color = Color.Gray
                )
                Switch(checked = checked.value, onCheckedChange = { checked.value = it })
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Hide profile image for unknown users",
                    modifier = modifier.padding(top = 10.dp),
                    color = Color.Gray
                )
                Switch(checked = checked.value, onCheckedChange = { checked.value = it })
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Show status",
                    modifier = modifier.padding(top = 10.dp),
                    color = Color.Gray
                )
                Switch(checked = checked.value, onCheckedChange = { checked.value = it })
            }
        }
    }
}
