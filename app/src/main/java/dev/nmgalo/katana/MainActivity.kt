package dev.nmgalo.katana

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import dev.nmgalo.feature.messenger.ServiceLocator
import dev.nmgalo.katana.ui.KatanaApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { KatanaApp() }
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(newBase)
        ServiceLocator.initWithContext(newBase)
    }
}
