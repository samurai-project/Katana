package dev.nmgalo.katana.ui.composition

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val LocalBackgroundTheme = staticCompositionLocalOf { BackgroundTheme() }

@Immutable
data class BackgroundTheme(
    val color: Color = Color.Unspecified
)
