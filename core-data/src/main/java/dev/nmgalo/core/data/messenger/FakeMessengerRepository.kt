package dev.nmgalo.core.data.messenger

import dev.nmgalo.core.common.Dispatcher
import dev.nmgalo.core.common.KatanaDispatchers
import dev.nmgalo.core.common.di.FakeImplementation
import dev.nmgalo.core.model.messenger.Message
import dev.nmgalo.core.model.messenger.MessageStatus
import dev.nmgalo.core.network.KatanaNetworkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FakeMessengerRepository @Inject constructor(
    @Dispatcher(KatanaDispatchers.IO)
    private val io: CoroutineDispatcher,
    @FakeImplementation
    private val fakeDataSource: KatanaNetworkDataSource
) : MessengerRepository {

    override fun getMessagesByChatId(conversationId: Long): Flow<List<Message>> = flow {
        emit(fakeDataSource.getConversation(1).map { data ->
            Message(
                id = data.id,
                message = data.message,
                senderId = data.senderId,
                status = when (data.status) {
                    MESSAGE_STATUS_SENT -> MessageStatus.SENT
                    MESSAGE_STATUS_READ -> MessageStatus.READ
                    else -> MessageStatus.PENDING
                },
                createdAt = data.createdAt
            )
        }.sortedByDescending { it.id }) // Same for database, We should use ORDER BY id or date DESC
    }.flowOn(io)

    companion object {
        const val MESSAGE_STATUS_SENT = 2
        const val MESSAGE_STATUS_READ = 3
    }
}
