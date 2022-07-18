package dev.nmgalo.core.network

import dev.nmgalo.core.model.wall.Wall

/**
 * Interface representing network calls to the Katana backend
 */
interface KatanaNetworkDataSource {
    suspend fun getWall(): List<Wall>

}
