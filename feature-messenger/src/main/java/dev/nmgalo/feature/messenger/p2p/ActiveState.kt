package dev.nmgalo.feature.messenger.p2p

import android.annotation.SuppressLint
import android.view.LayoutInflater
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.PhoneDisabled
import androidx.compose.material.icons.filled.VideocamOff
import androidx.compose.material.icons.filled.VolumeOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import dev.nmgalo.core.ui.ChatActionItem
import dev.nmgalo.core.ui.withAlpha
import dev.nmgalo.feature.messenger.R
import dev.nmgalo.feature.messenger.databinding.WebrtcSurfaceViewRendererBinding

@SuppressLint("InflateParams")
@Composable
fun ActiveChat(
    modifier: Modifier = Modifier,
    activeCallViewModel: ActiveCallViewModel = hiltViewModel()
) {

    val webRtcSessionManager = activeCallViewModel.webRtcSessionManager

    var binding: WebrtcSurfaceViewRendererBinding? = null

    Box(
        modifier = Modifier
            .background(Color.Gray)
            .fillMaxSize(),
    ) {

        AndroidView(factory = { context ->
            val view = LayoutInflater.from(context)
                .inflate(R.layout.webrtc_surface_view_renderer, null, false)
            binding = WebrtcSurfaceViewRendererBinding.bind(view)
            binding?.remoteView?.let {
                it.init(activeCallViewModel.eglBaseContext, null)
                it.setEnableHardwareScaler(true)
            }
            binding?.localVideo?.let {
                it.init(activeCallViewModel.eglBaseContext, null)
                it.setEnableHardwareScaler(true)
                it.setMirror(true)
            }
            view
        })

        webRtcSessionManager.localVideoSinkFlow.collectAsState().let {
            it.value?.addSink(binding?.localVideo)
        }

        webRtcSessionManager.remoteVideoSinkFlow.collectAsState().let {
            it.value?.addSink(binding?.remoteView)
        }

        webRtcSessionManager.onSessionScreenReady()

        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .background(Color.Black.withAlpha(alpha = 0.2f)),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                ChatActionItem(imageVector = Icons.Filled.VideocamOff)
                ChatActionItem(imageVector = Icons.Filled.VolumeOff)
                ChatActionItem(imageVector = Icons.Filled.PersonAdd)
                ChatActionItem(imageVector = Icons.Filled.PhoneDisabled, background = Color.Red) {
                }
            }
        }
    }
}
