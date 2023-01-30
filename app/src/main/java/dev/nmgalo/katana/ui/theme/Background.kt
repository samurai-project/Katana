package dev.nmgalo.katana.ui.theme

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dev.nmgalo.katana.ui.composition.LocalBackgroundTheme

@Composable
fun KatanaBackground(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val color = LocalBackgroundTheme.current.color
    Surface(
        color = if (color == Color.Unspecified) Color.Transparent else color,
        modifier = modifier.fillMaxSize(),
    ) { content() }
}
