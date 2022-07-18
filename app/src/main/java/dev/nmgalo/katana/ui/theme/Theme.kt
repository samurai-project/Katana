package dev.nmgalo.katana.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider


private val DarkColorPalette = darkColorScheme(
    primary = Purple200,
    secondary = Teal200
)

private val LightColorPalette = lightColorScheme(
    primary = Purple500,
    secondary = Teal200
)

@Composable
fun KatanaTheme(
    darkTheme: Boolean = false, // TODO(nmgalo): Use isSystemInDarkTheme() in the future.
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
