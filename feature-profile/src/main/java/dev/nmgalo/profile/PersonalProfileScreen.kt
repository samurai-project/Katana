package dev.nmgalo.profile

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import dev.nmgalo.feature.profile.R

@Composable
fun PersonalProfileScreen() {
    Column {
        UserProfileInfo()
    }
}

@Composable
fun UserProfileInfo(modifier: Modifier = Modifier) {
    AsyncImage(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp),
        contentScale = ContentScale.Crop,
        model = "https://loremflickr.com/1080/436",
        contentDescription = null
    )
    Column(
        modifier = modifier
            .padding(horizontal = 10.dp)
            .offset(y = -60.dp)
    ) {
        UserProfile()
        UserName()
        FollowersAndFollowingCount()
    }
}

@Composable
fun UserProfile() {
    Row {
        AsyncImage(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape),
            model = "https://loremflickr.com/100/100",
            contentDescription = null
        )
    }
}

@Composable
fun UserName(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(top = 10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Nika Mgaloblishvili",
            style = MaterialTheme.typography.headlineSmall,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )
        Button(
            modifier = modifier.width(120.dp),
            colors = buttonColors(MaterialTheme.colorScheme.secondary),
            onClick = {}) {
            Text(stringResource(id = R.string.follow))
            Spacer(modifier = modifier.width(2.dp))
            Icon(Icons.Default.PersonAdd, "Follow")
        }
    }
}

// TODO remove later
const val MOCK_FOLLOWERS_COUNT = 24
const val MOCK_FOLLOWING_COUNT = 12

@Composable
fun FollowersAndFollowingCount() {
    Row(
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = formatFollowersOrFollowing(R.string.following, MOCK_FOLLOWING_COUNT),
            style = MaterialTheme.typography.labelSmall
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = formatFollowersOrFollowing(R.string.followers, MOCK_FOLLOWERS_COUNT),
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Composable
fun formatFollowersOrFollowing(@StringRes resource: Int, count: Int) = buildAnnotatedString {
    append("$count ")
    withStyle(style = SpanStyle(MaterialTheme.colorScheme.secondary)) {
        append(stringResource(id = resource))
    }
}
