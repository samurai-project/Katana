package dev.nmgalo.core.network

import dev.nmgalo.core.network.model.users.UserDTO
import dev.nmgalo.core.network.model.wall.WallDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface KatanaNetworkApi {
    @GET("posts")
    suspend fun getWall(): List<WallDTO>

    @GET("users")
    suspend fun getAllUser(): List<UserDTO>

    @GET("users/{userId}")
    suspend fun getUserById(@Path("userId") userId: Long): UserDTO
}
