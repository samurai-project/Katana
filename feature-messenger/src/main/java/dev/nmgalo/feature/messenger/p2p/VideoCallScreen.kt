package dev.nmgalo.feature.messenger.p2p

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import dev.nmgalo.feature.messenger.p2p.rtc.WebRTCSessionState

@Composable
fun GroupCallScreen(
    callerViewModel: CallerViewModel = hiltViewModel()
) {

    val rtcSessionState = callerViewModel.signalingClient.sessionStateFlow.collectAsState()

    when (rtcSessionState.value) {
        WebRTCSessionState.Active -> ActiveChat()
        WebRTCSessionState.Creating -> CreatingState()
        WebRTCSessionState.Ready -> ReadyState()
        WebRTCSessionState.Impossible -> ImpossibleState()
        WebRTCSessionState.Offline -> OfflineState()
    }
}
