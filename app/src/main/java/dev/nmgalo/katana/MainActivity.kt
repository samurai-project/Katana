package dev.nmgalo.katana

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import dagger.hilt.android.AndroidEntryPoint
import dev.nmgalo.feature.messenger.p2p.ServiceLocator
import dev.nmgalo.katana.ui.KatanaApp
import dev.nmgalo.katana.ui.composition.LocalOnFinishDispatcher
import dev.nmgalo.katana.ui.theme.KatanaTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KatanaTheme {
                CompositionLocalProvider(LocalOnFinishDispatcher provides { finish() }) {
                    KatanaApp()
                }
            }
        }
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(newBase)
        ServiceLocator.initWithContext(newBase)
    }
}
