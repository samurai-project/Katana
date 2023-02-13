package dev.nmgalo.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.nmgalo.feature.settings.R

@Composable
fun GeneralSettingsScreen(
    viewModel: GeneralScreenViewModel = hiltViewModel()
) {
    val checked = remember { mutableStateOf(viewModel.isDarkModeEnabled.value) }

    DisposableEffect(checked.value) {
        viewModel.setDarkModeIsEnabled(checked.value)
        onDispose { }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.enable_dark_mode))
            Switch(checked = checked.value, onCheckedChange = {
                checked.value = it
            })
        }
    }
}
