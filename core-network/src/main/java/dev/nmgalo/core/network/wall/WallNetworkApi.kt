package dev.nmgalo.core.network.wall

import dev.nmgalo.core.network.model.users.UserDTO
import dev.nmgalo.core.network.wall.model.WallDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface WallNetworkApi {
    @GET("posts")
    suspend fun getWall(): List<WallDTO>

    @GET("users")
    suspend fun getAllUser(): List<UserDTO>

    @GET("users/{userId}")
    suspend fun getUserById(@Path("userId") userId: Long): UserDTO
}
