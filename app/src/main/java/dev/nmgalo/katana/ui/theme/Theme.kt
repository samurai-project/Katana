package dev.nmgalo.katana.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import dev.nmgalo.katana.ui.composition.BackgroundTheme
import dev.nmgalo.katana.ui.composition.LocalBackgroundTheme


private val DarkThemeColors = darkColorScheme(
    primary = Color(RED_300),
    onPrimary = Color.Black,
    secondary = Color(RED_700),
    onSecondary = Color.Black,
    error = Color(RED_800),
    onBackground = Color.White,
)

private val LightThemeColors = lightColorScheme(
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

    val colorScheme = if (darkTheme) DarkThemeColors else LightThemeColors
    val backgroundTheme = BackgroundTheme(color = colorScheme.surface)

    CompositionLocalProvider(
        LocalBackgroundTheme provides backgroundTheme
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = KatanaTypography,
            content = content
        )
    }
}
