package dev.nmgalo.feature.messenger.model

data class Chat(
    val id: Long,
    val userName: String,
    val userProfilePicture: String,
    val lastMessage: String
)
