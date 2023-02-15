package dev.nmgalo.core.network

import dev.nmgalo.core.network.fake.model.MessageDTO
import dev.nmgalo.core.network.model.users.UserDTO
import dev.nmgalo.core.network.model.wall.WallDTO

/**
 * Interface representing network calls to the Katana backend
 */
interface KatanaNetworkDataSource {
    suspend fun getWall(): List<WallDTO>

    suspend fun getUserById(userId: Long): UserDTO

    suspend fun getAllUser(): List<UserDTO>

    suspend fun getConversation(conversationId: Long): List<MessageDTO>
}
