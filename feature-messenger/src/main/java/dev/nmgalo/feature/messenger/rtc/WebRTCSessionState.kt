package dev.nmgalo.feature.messenger.rtc

enum class WebRTCSessionState {
    /*
     * Offer and Answer messages has been sent
     **/
    Active,

    /*
     * Creating session, offer has been sent
     **/
    Creating,

    /*
     * Both clients available and ready to initiate session
     **/
    Ready,

    /*
     * We have less than two clients connected to the server
     **/
    Impossible,

    /*
     * unable to connect signaling server
     **/
    Offline
}
