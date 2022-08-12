package dev.nmgalo.core.data.wall

import dev.nmgalo.core.common.Dispatcher
import dev.nmgalo.core.common.KatanaDispatchers
import dev.nmgalo.core.model.wall.Wall
import dev.nmgalo.core.network.KatanaNetworkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WallRepositoryImpl @Inject constructor(
    private val network: KatanaNetworkDataSource,
    @Dispatcher(KatanaDispatchers.IO)
    private val io: CoroutineDispatcher
) : WallRepository {

    override fun getWall(): Flow<List<Wall>> = flow {
        emit(network.getWall())
    }.flowOn(io)

}
