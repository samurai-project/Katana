package dev.nmgalo.feature.messenger.p2p

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.nmgalo.feature.messenger.p2p.rtc.session.WebRtcSessionManager
import org.webrtc.EglBase
import javax.inject.Inject

@HiltViewModel
class ActiveCallViewModel @Inject constructor(
    val webRtcSessionManager: WebRtcSessionManager,
    val eglBaseContext: EglBase.Context
) : ViewModel()
