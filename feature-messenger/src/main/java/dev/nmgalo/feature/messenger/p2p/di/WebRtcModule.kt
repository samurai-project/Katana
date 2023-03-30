package dev.nmgalo.feature.messenger.p2p.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.nmgalo.feature.messenger.p2p.rtc.SignalingClient
import dev.nmgalo.feature.messenger.p2p.rtc.session.PeerConnectionUtils
import dev.nmgalo.feature.messenger.p2p.rtc.session.WebRtcSessionManager
import org.webrtc.EglBase

@Module
@InstallIn(ViewModelComponent::class)
object WebRtcModule {

    @Provides
    fun providesContext(@ApplicationContext context: Context) = context

    @Provides
    fun providesSignalingClient(): SignalingClient = SignalingClient()

    @Provides
    fun providesEglBaseContext(): EglBase.Context = EglBase.create().eglBaseContext

    @Provides
    fun providesPeerConnectionUtils(
        context: Context,
        eglContext: EglBase.Context
    ): PeerConnectionUtils = PeerConnectionUtils(context, eglContext)

    @Provides
    fun providesWebRtcSessionManager(
        context: Context,
        signalingClient: SignalingClient,
        peerConnectionUtils: PeerConnectionUtils
    ) = WebRtcSessionManager(context, signalingClient, peerConnectionUtils)
}
