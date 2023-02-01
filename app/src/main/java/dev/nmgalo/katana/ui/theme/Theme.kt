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
    primary = Color(Blue80),
    onPrimary = Color(Blue20),
    primaryContainer = Color(Blue30),
    onPrimaryContainer = Color(Blue90),
    inversePrimary = Color(Blue40),
    secondary = Color(DarkBlue80),
    onSecondary = Color(DarkBlue20),
    secondaryContainer = Color(DarkBlue30),
    onSecondaryContainer = Color(DarkBlue90),
    tertiary = Color(Yellow80),
    onTertiary = Color(Yellow20),
    tertiaryContainer = Color(Yellow30),
    onTertiaryContainer = Color(Yellow90),
    error = Color(Red80),
    onError = Color(Red20),
    errorContainer = Color(Red30),
    onErrorContainer = Color(Red90),
    background = Color(Grey10),
    onBackground = Color(Grey90),
    surface = Color(Grey10),
    onSurface = Color(Grey80),
    inverseSurface = Color(Grey90),
    inverseOnSurface = Color(Grey20),
    surfaceVariant = Color(BlueGrey30),
    onSurfaceVariant = Color(BlueGrey80),
    outline = Color(BlueGrey60)
)

private val LightThemeColors = lightColorScheme(
    primary = Color(Blue40),
    onPrimary = Color.White,
    primaryContainer = Color(Blue90),
    onPrimaryContainer = Color(Blue10),
    inversePrimary = Color(Blue80),
    secondary = Color(DarkBlue40),
    onSecondary = Color.White,
    secondaryContainer = Color(DarkBlue90),
    onSecondaryContainer = Color(DarkBlue10),
    tertiary = Color(Yellow40),
    onTertiary = Color.White,
    tertiaryContainer = Color(Yellow90),
    onTertiaryContainer = Color(Yellow10),
    error = Color(Red40),
    onError = Color.White,
    errorContainer = Color(Red90),
    onErrorContainer = Color(Red10),
    background = Color(Grey99),
    onBackground = Color(Grey10),
    surface = Color(Grey99),
    onSurface = Color(Grey10),
    inverseSurface = Color(Grey20),
    inverseOnSurface = Color(Grey95),
    surfaceVariant = Color(BlueGrey90),
    onSurfaceVariant = Color(BlueGrey30),
    outline = Color(BlueGrey50)
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
