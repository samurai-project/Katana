package dev.nmgalo.feature.messenger.p2p

import android.content.Context
import dev.nmgalo.feature.messenger.p2p.rtc.SignalingClient
import dev.nmgalo.feature.messenger.p2p.rtc.session.PeerConnectionUtils
import dev.nmgalo.feature.messenger.p2p.rtc.session.WebRtcSessionManager
import org.webrtc.EglBase
import java.lang.ref.WeakReference

object ServiceLocator {
    private lateinit var context: WeakReference<Context>
    val signalingClient = SignalingClient()

    private val peerConnectionUtils by lazy {
        PeerConnectionUtils(
            context.get() ?: error("context has not been initialized"),
            eglBaseContext
        )
    }

    val webRtcSessionManager by lazy {
        WebRtcSessionManager(
            context.get() ?: error("context has not been initialized"),
            signalingClient,
            peerConnectionUtils
        )
    }

    val eglBaseContext: EglBase.Context = EglBase.create().eglBaseContext

    fun initWithContext(context: Context) {
        ServiceLocator.context = WeakReference<Context>(context)
    }

    init {
        System.loadLibrary("jingle_peerconnection_so")
    }
}
