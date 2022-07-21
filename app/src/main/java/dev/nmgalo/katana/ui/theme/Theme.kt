package dev.nmgalo.katana.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color


private val DarkThemeColors = darkColorScheme(
    primary = Color(RED_300),
    onPrimary = Color.Black,
    secondary = Color(RED_700),
    onSecondary = Color.Black,
    error = Color(RED_800),
    onBackground = Color.Black,
)

private val LightThemeColors = lightColorScheme(
    primary = Color(RED_700),
    onPrimary = Color.White,
    secondary = Color(RED_700),
    onSecondary = Color.White,
    error = Color(RED_800),
    onBackground = Color.White,
)

@Composable
fun KatanaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    CompositionLocalProvider {
        MaterialTheme(
            colorScheme = if (darkTheme) DarkThemeColors else LightThemeColors,
            typography = KatanaTypography,
            content = content
        )
    }
}
