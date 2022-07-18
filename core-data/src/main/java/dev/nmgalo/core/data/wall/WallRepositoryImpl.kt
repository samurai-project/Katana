package dev.nmgalo.core.data.wall

import dev.nmgalo.core.model.wall.Wall
import dev.nmgalo.core.network.KatanaNetworkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class WallRepositoryImpl @Inject constructor(
    private val network: KatanaNetworkDataSource
) : WallRepository {
    // TODO remove Flow from this call !! important
    override suspend fun getWall(): Flow<List<Wall>> = flow {
        emit(network.getWall())
    }
}
