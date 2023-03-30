package dev.nmgalo.feature.messenger.p2p

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.nmgalo.feature.messenger.p2p.rtc.SignalingClient
import javax.inject.Inject

@HiltViewModel
class CallerViewModel @Inject constructor(
    val signalingClient: SignalingClient
) : ViewModel()
