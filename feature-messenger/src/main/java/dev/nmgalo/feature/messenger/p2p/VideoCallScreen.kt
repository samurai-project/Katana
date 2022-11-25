package dev.nmgalo.feature.messenger.p2p

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import dev.nmgalo.feature.messenger.p2p.rtc.WebRTCSessionState

@Composable
fun GroupCallScreen(navController: NavController) {

    val rtcSessionState = ServiceLocator.signalingClient.sessionStateFlow.collectAsState()

    when (rtcSessionState.value) {
        WebRTCSessionState.Active -> ActiveChat(navController = navController)
        WebRTCSessionState.Creating -> CreatingState()
        WebRTCSessionState.Ready -> ReadyState()
        WebRTCSessionState.Impossible -> ImpossibleState()
        WebRTCSessionState.Offline -> OfflineState()
    }
}
