package dev.nmgalo.feature.messenger

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.PhoneDisabled
import androidx.compose.material.icons.filled.VideocamOff
import androidx.compose.material.icons.filled.VolumeOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.nmgalo.core.ui.withAlpha

@Composable
fun GroupCallScreen(navController: NavController, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .background(Color.Gray)
            .fillMaxSize(),
    ) {
        Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .background(Color.Black.withAlpha(alpha = 0.2f)),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                ChatActionItem(imageVector = Icons.Filled.VideocamOff) {}
                ChatActionItem(imageVector = Icons.Filled.VolumeOff) {}
                ChatActionItem(imageVector = Icons.Filled.PersonAdd) {}
                ChatActionItem(imageVector = Icons.Filled.PhoneDisabled, background = Color.Red) {
                    navController.popBackStack()
                }
            }
        }
    }
}

@Composable
fun ChatActionItem(
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    background: Color = Color.Gray,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier
            .padding(vertical = 18.dp)
            .clip(CircleShape)
            .background(background),
        onClick = onClick
    ) {
        Icon(
            imageVector = imageVector,
            tint = Color.White,
            modifier = modifier
                .width(30.dp)
                .height(30.dp),
            contentDescription = null
        )
    }
}

