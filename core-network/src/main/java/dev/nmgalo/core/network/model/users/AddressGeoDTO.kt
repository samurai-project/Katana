package dev.nmgalo.core.network.model.users

@kotlinx.serialization.Serializable
data class AddressGeoDTO(
    val lat: String,
    val lng: String
)
