package dev.nmgalo.feature.messenger.model

data class User(
    val id: Long,
    val name: String,
    val profilePicUrl: String? = null
)
