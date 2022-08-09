package dev.nmgalo.core.data.wall

import dev.nmgalo.core.model.wall.Wall
import kotlinx.coroutines.flow.Flow

interface WallRepository {
    fun getWall(): Flow<List<Wall>>
}
