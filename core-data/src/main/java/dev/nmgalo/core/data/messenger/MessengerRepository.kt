package dev.nmgalo.core.data.messenger

import dev.nmgalo.core.model.messenger.Message
import kotlinx.coroutines.flow.Flow

interface MessengerRepository {
    fun getConversationById(conversationId: Long): Flow<List<Message>>
}
