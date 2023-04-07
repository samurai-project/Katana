package dev.nmgalo.core.network.messenger

import dev.nmgalo.core.network.messenger.model.ChatUsersDTO
import dev.nmgalo.core.network.messenger.model.SendMessageDTO
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MessengerNetworkClient @Inject constructor(

) : MessengerNetworkDataSource {

    override suspend fun getConversation(conversationId: Long) = TODO("Not yet implemented")

    override suspend fun getChats() = TODO("Not yet implemented")

    override suspend fun getChatUsers(chatId: Long): List<ChatUsersDTO> =
        TODO("Not yet implemented")

    override suspend fun sendMessage(message: SendMessageDTO) = TODO("Not yet implemented")
}
