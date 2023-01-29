package dev.nmgalo.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import coil.compose.AsyncImage
import dev.nmgalo.core.ui.DevicePreviews

@Composable
fun PersonalProfileScreen() {
    Box {
        AsyncImage(model = "https://loremflickr.com/536/536", contentDescription = null)
        Text("Personal Profile nick")
    }
}


@DevicePreviews
@Composable
fun Preview() {
    PersonalProfileScreen()
}
