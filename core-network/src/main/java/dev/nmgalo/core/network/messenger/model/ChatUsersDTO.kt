package dev.nmgalo.core.network.messenger.model

@kotlinx.serialization.Serializable
data class ChatUsersDTO(
    val id: Long,
    val name: String,
    val profilePicUrl: String
)
