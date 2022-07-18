package dev.nmgalo.core.data.wall

import dev.nmgalo.core.network.KatanaNetworkDataSource
import javax.inject.Inject

class WallRepositoryImpl @Inject constructor(
    private val network: KatanaNetworkDataSource
) : WallRepository {
    override suspend fun getWall() = network.getWall()
}
