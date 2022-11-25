package dev.nmgalo.feature.messenger.p2p.rtc

enum class SignalingCommand {
    /*
     *  Command for WebRTCSessionState
     **/
    STATE,

    /*
     * to send or receive offer
     **/
    OFFER,

    /*
     *  to send or receive answer
     **/
    ANSWER,

    /*
     * to send and receive ice candidates
     **/
    ICE
}
