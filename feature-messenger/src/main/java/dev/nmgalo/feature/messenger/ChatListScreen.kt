package dev.nmgalo.feature.messenger

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Suppress("LongMethod")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChatListScreen(
    onItemClick: (route: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        items(6) { _ ->
            Row(
                modifier = modifier
                    .padding(all = 10.dp)
                    .fillMaxSize()
                    .combinedClickable(
                        onLongClick = {
                            TODO("Later !!!")
                        },
                        onClick = {
                            val mockChatId = 1L
                            onItemClick("chat/$mockChatId")
                        }
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = "https://loremflickr.com/536/536",
                    contentDescription = "Contact profile picture",
                    modifier = modifier
                        .padding(all = 5.dp)
                        .size(70.dp)
                        .clip(CircleShape)
                        .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape)
                )

                Spacer(modifier = modifier.width(15.dp))

                Column {
                    Text(text = "Jane doe", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = modifier.height(2.dp))
                    Text(
                        text = "ნიკა გამარჯობა, მე ვარ ბონო, ნახალოვკლეი ბონო :დდდ",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            }
        }
    }
}

