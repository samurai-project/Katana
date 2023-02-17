package dev.nmgalo.core.data.messenger

import dev.nmgalo.core.model.chat.Chat
import dev.nmgalo.core.model.messenger.ChatUser
import dev.nmgalo.core.model.messenger.Message
import kotlinx.coroutines.flow.Flow

interface MessengerRepository {

    fun fetchNextChats(): Flow<Unit>

    fun getAllChats(): Flow<List<Chat>>

    fun fetchChatUsers(chatId: Long): Flow<Unit>

    fun getChatUsers(userIds: IntArray): Flow<List<ChatUser>>

    fun fetchNextMessages(chatId: Long): Flow<Unit>

    fun getAllMessageByChatId(chatId: Long): Flow<List<Message>>

    fun sendMessage(chatId: Long, text: String): Flow<Unit>

}
