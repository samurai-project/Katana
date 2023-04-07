package dev.nmgalo.core.data.wall

import dev.nmgalo.core.common.Dispatcher
import dev.nmgalo.core.common.KatanaDispatchers
import dev.nmgalo.core.model.wall.Wall
import dev.nmgalo.core.network.wall.WallNetworkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DefaultWallRepository @Inject constructor(
    private val network: WallNetworkDataSource,
    @Dispatcher(KatanaDispatchers.IO)
    private val io: CoroutineDispatcher
) : WallRepository {

    override fun getWall(): Flow<List<Wall>> = flow {
        emit(
            network.getWall().map {
                Wall(
                    id = it.id,
                    title = it.title
                )
            }
        )
    }.flowOn(io)

}
