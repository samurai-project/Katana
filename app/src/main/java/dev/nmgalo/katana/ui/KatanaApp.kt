package dev.nmgalo.katana.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import dev.nmgalo.katana.ui.theme.KatanaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KatanaApp() {
    KatanaTheme {
        Scaffold(
            containerColor = Color.Transparent,
            contentColor = MaterialTheme.colorScheme.onBackground,
        ) { _ ->
            Text(text = "Nick is awesome")
        }
    }
}
