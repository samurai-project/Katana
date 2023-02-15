package dev.nmgalo.core.model.chat

data class Chat(
    val id: Long,
    val userName: String,
    val userProfilePicture: String,
    val lastMessage: String
)
