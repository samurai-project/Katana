package dev.nmgalo.core.network.wall

import dev.nmgalo.core.network.model.users.UserDTO
import dev.nmgalo.core.network.wall.model.WallDTO
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WallNetworkClient @Inject constructor(
    private val networkApi: WallNetworkApi
) : WallNetworkDataSource {

    override suspend fun getWall(): List<WallDTO> =
        networkApi.getWall()

    override suspend fun getUserById(userId: Long): UserDTO = networkApi.getUserById(userId)

    override suspend fun getAllUser(): List<UserDTO> = networkApi.getAllUser()
}
