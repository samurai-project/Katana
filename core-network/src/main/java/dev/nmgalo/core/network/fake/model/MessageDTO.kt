package dev.nmgalo.core.network.fake.model

@kotlinx.serialization.Serializable
data class MessageDTO(
    val id: Long,
    val message: String,
    val senderId: Long,
    val status: Int,
    val createdAt: Long,
)
