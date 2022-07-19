package dev.nmgalo.katana.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color


private val DarkColorPalette = darkColorScheme(
    primary = Color(PURPLE_200),
    secondary = Color(TEAL_200)
)

private val LightColorPalette = lightColorScheme(
    primary = Color(PURPLE_500),
    secondary = Color(TEAL_200)
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
