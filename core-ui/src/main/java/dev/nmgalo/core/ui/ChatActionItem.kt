package dev.nmgalo.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun ChatActionItem(
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    background: Color = Color.Gray,
    onClick: () -> Unit = {}
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
