package dev.nmgalo.core.network.model.users

@kotlinx.serialization.Serializable
data class UserDTO(
    val id: Long,
    val name: String,
    val username: String,
    val email: String,
    val address: AddressDTO,
    val phone: String,
    val website: String,
    val company: CompanyDTO
)
