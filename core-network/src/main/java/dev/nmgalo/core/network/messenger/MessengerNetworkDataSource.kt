package dev.nmgalo.core.network.messenger

import dev.nmgalo.core.network.messenger.model.ChatDTO
import dev.nmgalo.core.network.messenger.model.ChatUsersDTO
import dev.nmgalo.core.network.messenger.model.MessageDTO
import dev.nmgalo.core.network.messenger.model.SendMessageDTO

interface MessengerNetworkDataSource {

    suspend fun getConversation(conversationId: Long): List<MessageDTO>

    suspend fun getChats(): List<ChatDTO>

    suspend fun getChatUsers(chatId: Long): List<ChatUsersDTO>

    suspend fun sendMessage(message: SendMessageDTO)
}
