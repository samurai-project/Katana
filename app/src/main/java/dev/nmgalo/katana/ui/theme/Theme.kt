package dev.nmgalo.katana.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color


private val DarkColorPalette = darkColorScheme(
    primary = Color(RED_300),
    onPrimary = Color.Black,
    secondary = Color(RED_700),
    error = Color(RED_800),
    onBackground = Color.Black,
)

private val LightColorPalette = lightColorScheme(
    primary = Color(RED_700),
    onPrimary = Color.White,
    secondary = Color(RED_700),
    onSecondary = Color.White,
    error = Color(RED_800),
    onBackground = Color.Black,
)

@Composable
fun KatanaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    CompositionLocalProvider {
        MaterialTheme(
            colorScheme = colors,
            typography = KatanaTypography,
            content = content
        )
    }
}
