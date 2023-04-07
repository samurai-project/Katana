package dev.nmgalo.core.network.wall

import dev.nmgalo.core.network.model.users.UserDTO
import dev.nmgalo.core.network.wall.model.WallDTO

/**
 * Interface representing network calls to the Katana backend
 */
interface WallNetworkDataSource {

    suspend fun getWall(): List<WallDTO>

    suspend fun getUserById(userId: Long): UserDTO

    suspend fun getAllUser(): List<UserDTO>

}
