package dev.nmgalo.core.data.messenger

import dev.nmgalo.core.model.messenger.Message
import kotlinx.coroutines.flow.Flow

interface MessengerRepository {

    fun fetchNextMessages(chatId: Long): Flow<Unit>

    fun getAllByChatId(chatId: Long): Flow<List<Message>>
}
