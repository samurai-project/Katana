package dev.nmgalo.feature.messenger.model

data class Message(
    val id: Long,
    val message: String,
    val isMe: Boolean
)
