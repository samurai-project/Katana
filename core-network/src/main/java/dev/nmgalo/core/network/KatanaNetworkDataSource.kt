package dev.nmgalo.core.network

/**
 * Interface representing network calls to the Katana backend
 */
interface KatanaNetworkDataSource {
    suspend fun getWall(): List<String>

}

