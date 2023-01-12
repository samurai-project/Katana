package dev.nmgalo.feature.messenger.p2p.rtc

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener

private const val SIGNALING_URL = "ws://192.168.100.9:8080/rtc"

class SignalingClient {
    private val signalingScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private val client = OkHttpClient()
    private val request = Request.Builder()
        .url(SIGNALING_URL)
        .build()

    private val ws = client.newWebSocket(request, SignalingWebSocketListener())

    private val _sessionStateFlow = MutableStateFlow(WebRTCSessionState.Offline)
    val sessionStateFlow: StateFlow<WebRTCSessionState> = _sessionStateFlow

    private val _signalingCommandFlow = MutableSharedFlow<Pair<SignalingCommand, String>>()
    val signalingCommandFlow: SharedFlow<Pair<SignalingCommand, String>> = _signalingCommandFlow

    fun sendCommand(signalingCommand: SignalingCommand, message: String) {
        ws.send("$signalingCommand $message")
    }

    private inner class SignalingWebSocketListener : WebSocketListener() {
        override fun onMessage(webSocket: WebSocket, text: String) {
            when {
                text.startsWith(SignalingCommand.STATE.toString(), true) ->
                    handleStateMessage(text)
                text.startsWith(SignalingCommand.OFFER.toString(), true) ->
                    handleSignalingCommand(SignalingCommand.OFFER, text)
                text.startsWith(SignalingCommand.ANSWER.toString(), true) ->
                    handleSignalingCommand(SignalingCommand.ANSWER, text)
                text.startsWith(SignalingCommand.ICE.toString(), true) ->
                    handleSignalingCommand(SignalingCommand.ICE, text)
            }
        }
    }

    private fun handleStateMessage(message: String) {
        val state = getSeparatedMessage(message)
        _sessionStateFlow.value = WebRTCSessionState.valueOf(state)
    }

    private fun handleSignalingCommand(command: SignalingCommand, text: String) {
        val value = getSeparatedMessage(text)
        signalingScope.launch {
            _signalingCommandFlow.emit(command to value)
        }
    }

    private fun getSeparatedMessage(text: String) = text.substringAfter(' ')
}

