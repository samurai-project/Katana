package dev.nmgalo.core.data.messenger

import dev.nmgalo.core.model.chat.Chat
import dev.nmgalo.core.model.messenger.Message
import kotlinx.coroutines.flow.Flow

interface MessengerRepository {

    fun fetchNextChats(): Flow<Unit>

    fun getAllChats(): Flow<List<Chat>>

    fun fetchNextMessages(chatId: Long): Flow<Unit>

    fun getAllByChatId(chatId: Long): Flow<List<Message>>

    fun sendMessage(text: String): Flow<Unit>
}
