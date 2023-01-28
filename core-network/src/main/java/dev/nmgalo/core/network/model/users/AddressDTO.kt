package dev.nmgalo.core.network.model.users

@kotlinx.serialization.Serializable
data class AddressDTO(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: AddressGeoDTO
)
