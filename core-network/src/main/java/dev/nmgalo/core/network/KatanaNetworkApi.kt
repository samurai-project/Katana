package dev.nmgalo.core.network

import dev.nmgalo.core.network.model.WallDTO
import retrofit2.http.GET

interface KatanaNetworkApi {
    @GET("posts")
    suspend fun getWall(): List<WallDTO>
}
