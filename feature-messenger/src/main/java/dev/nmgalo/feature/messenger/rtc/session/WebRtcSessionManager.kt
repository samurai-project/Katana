package dev.nmgalo.feature.messenger.rtc.session

import android.content.Context
import dev.nmgalo.feature.messenger.rtc.SignalingClient
import dev.nmgalo.feature.messenger.rtc.SignalingCommand
import java.util.UUID
import java.util.concurrent.Executors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import org.webrtc.*
import org.webrtc.MediaStreamTrack.VIDEO_TRACK_KIND

private const val VIDEO_WIDTH = 320
private const val VIDEO_HEIGHT = 240
private const val VIDEO_FPS = 30

private const val ICE_SEPARATOR = '$'

class WebRtcSessionManager(
    private val context: Context,
    private val signalingClient: SignalingClient,
    private val peerConnectionUtils: PeerConnectionUtils
) {
    private val sessionManagerScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private val peerConnectionExecutor = Executors.newSingleThreadExecutor()

    private val _localVideoSinkFlow = MutableSharedFlow<VideoTrack>()
    val localVideoSinkFlow: SharedFlow<VideoTrack> = _localVideoSinkFlow

    private val _remoteVideoSinkFlow = MutableSharedFlow<VideoTrack>()
    val remoteVideoSinkFlow: SharedFlow<VideoTrack> = _remoteVideoSinkFlow

    private val mediaConstraints = MediaConstraints().apply {
        mandatory.add(
            MediaConstraints.KeyValuePair(
                "OfferToReceiveVideo", "true"
            )
        )
    }

    private val videoCapturer = getFrontCameraCapturer()

    private val surfaceTextureHelper = SurfaceTextureHelper.create(
        "SurfaceTextureHelperThread", EglBase.create().eglBaseContext
    )

    private val peerConnectionFactory = peerConnectionUtils.peerConnectionFactory
    private val videoSource by lazy {
        peerConnectionFactory.createVideoSource(videoCapturer.isScreencast).apply {
            videoCapturer.initialize(surfaceTextureHelper, context, this.capturerObserver)
            videoCapturer.startCapture(VIDEO_HEIGHT, VIDEO_WIDTH, VIDEO_FPS)
        }
    }

    private val localVideoTrack: VideoTrack by lazy {
        peerConnectionFactory.createVideoTrack(
            "Video${UUID.randomUUID()}",
            videoSource
        )
    }

    private var offer: String? = null

    private val peerConnection: PeerConnection by lazy {
        createNewPeerConnection() ?: error("peer connection initialization failed")
    }

    init {
        sessionManagerScope.launch {
            signalingClient.signalingCommandFlow
                .collect { commandToValue ->
                    when (commandToValue.first) {
                        SignalingCommand.OFFER -> handleOffer(commandToValue.second)
                        SignalingCommand.ANSWER -> handleAnswer(commandToValue.second)
                        SignalingCommand.ICE -> handleIce(commandToValue.second)
                        else -> Unit
                    }
                }
        }
    }

    fun onSessionScreenReady() {
        peerConnectionExecutor.execute {
            peerConnection.addTrack(localVideoTrack)
            sessionManagerScope.launch {
                _localVideoSinkFlow.emit(localVideoTrack)
            }

            if (offer != null) {
                sendAnswer()
            } else {
                sendOffer()
            }
        }
    }

    private fun sendOffer() {
        peerConnection.createOffer(
            CallbackSdpObserver(
                onCreate = { offer ->
                    peerConnection.setLocalDescription(
                        CallbackSdpObserver(
                            onSuccess = {
                                signalingClient.sendCommand(
                                    SignalingCommand.OFFER, offer.description
                                )
                            }
                        ), offer
                    )
                }
            ),
            mediaConstraints
        )
    }

    private fun sendAnswer() {
        peerConnection.setRemoteDescription(
            CallbackSdpObserver(),
            SessionDescription(SessionDescription.Type.OFFER, offer)
        )
        peerConnection.createAnswer(
            CallbackSdpObserver(
                onCreate = { answer ->
                    peerConnection.setLocalDescription(
                        CallbackSdpObserver(
                            onSuccess = {
                                signalingClient.sendCommand(
                                    SignalingCommand.ANSWER,
                                    answer.description
                                )
                            }
                        ),
                        answer
                    )
                }
            ), mediaConstraints
        )
    }

    private fun handleOffer(sdp: String) {
        offer = sdp
    }

    private fun handleAnswer(sdp: String) {
        peerConnection.setRemoteDescription(
            CallbackSdpObserver(), SessionDescription(SessionDescription.Type.ANSWER, sdp)
        )
    }

    private fun handleIce(iceMessage: String) {
        val iceArray = iceMessage.split(ICE_SEPARATOR)
        peerConnectionExecutor.execute {
            peerConnection.apply {
                addIceCandidate(
                    IceCandidate(
                        iceArray[0], // sdpMid
                        iceArray[1].toInt(), //sdpMLineIndex
                        iceArray[2] // sdp
                    )
                )
            }
        }
    }

    private fun createNewPeerConnection(): PeerConnection? {
        return peerConnectionFactory.createPeerConnection(
            peerConnectionUtils.rtcConfig,
            PeerConnectionObserver(
                onIceCandidateCallback = { iceCandidate ->
                    signalingClient.sendCommand(
                        SignalingCommand.ICE,
                        "${iceCandidate.sdpMid}$ICE_SEPARATOR${iceCandidate.sdpMLineIndex}$ICE_SEPARATOR${iceCandidate.sdp}"
                    )
                },
                onTrackCallback = { rtpTransceiver ->
                    val track = rtpTransceiver?.receiver?.track() ?: return@PeerConnectionObserver
                    if (track.kind() == VIDEO_TRACK_KIND) {
                        val videoTrack = track as VideoTrack
                        sessionManagerScope.launch {
                            _remoteVideoSinkFlow.emit(videoTrack)
                        }
                    }
                }
            )
        )
    }

    private fun getFrontCameraCapturer(): VideoCapturer {
        val cameraEnumerator = Camera2Enumerator(context)
        cameraEnumerator.deviceNames.forEach { cameraName ->
            if (cameraEnumerator.isFrontFacing(cameraName)) {
                return cameraEnumerator.createCapturer(cameraName, null)
            }
        }
        error("Front camera does not exists")
    }
}