package dev.nmgalo.katana

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import dev.nmgalo.feature.messenger.p2p.ServiceLocator
import dev.nmgalo.katana.ui.KatanaApp
import dev.nmgalo.katana.ui.composition.LocalOnFinishDispatcher
import dev.nmgalo.katana.ui.onboarding.OnBoardingScreen
import dev.nmgalo.katana.ui.theme.KatanaTheme
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var uiState: UserDataState by mutableStateOf(UserDataState.Loading)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.onEach {
                    uiState = it
                }.collect()
            }
        }

        setContent {
            val darkTheme = shouldUseDarkTheme(uiState)
            val showOnBoardingScreen = shouldShowOnBoardingScreen(uiState)
            KatanaTheme(darkTheme = darkTheme) {
                CompositionLocalProvider(LocalOnFinishDispatcher provides { finish() }) {
                    if (showOnBoardingScreen) OnBoardingScreen() else KatanaApp()
                }
            }
        }
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(newBase)
        ServiceLocator.initWithContext(newBase)
    }
}

@Composable
private fun shouldUseDarkTheme(uiState: UserDataState): Boolean {
    return when (uiState) {
        UserDataState.Loading -> isSystemInDarkTheme()
        is UserDataState.Success -> uiState.userData.isDarkModeEnabled
    }
}

@Composable
fun shouldShowOnBoardingScreen(uiState: UserDataState): Boolean {
    return when (uiState) {
        UserDataState.Loading -> true
        is UserDataState.Success -> uiState.userData.shouldShowOnboardingScreen
    }
}
