package dev.nmgalo.profile

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import dev.nmgalo.core.ui.DevicePreviews

@Composable
fun PersonalProfileScreen() {
    Text("Personal Profile")
}


@DevicePreviews
@Composable
fun Preview() {
    PersonalProfileScreen()
}
