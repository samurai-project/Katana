package dev.nmgalo.core.network.model.users

@kotlinx.serialization.Serializable
data class CompanyDTO(
    val name: String,
    val catchPhrase: String,
    val bs: String
)
