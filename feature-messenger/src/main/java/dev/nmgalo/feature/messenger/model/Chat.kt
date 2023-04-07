package dev.nmgalo.feature.messenger.model

import dev.nmgalo.core.model.chat.Chat as ChatModel

data class Chat(
    val id: Long,
    val userName: String,
    val userProfilePicture: String,
    val lastMessage: String?
)

fun ChatModel.toUiModel() = Chat(
    id = this.id,
    userName = this.userName,
    userProfilePicture = this.userProfilePicture,
    lastMessage = this.lastMessage
)
