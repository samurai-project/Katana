package dev.nmgalo.core.data.messenger

import dev.nmgalo.core.common.Dispatcher
import dev.nmgalo.core.common.KatanaDispatchers
import dev.nmgalo.core.common.di.FakeImplementation
import dev.nmgalo.core.database.messenger.chat.Chat
import dev.nmgalo.core.database.messenger.chat.ChatDao
import dev.nmgalo.core.database.messenger.message.Message
import dev.nmgalo.core.database.messenger.message.MessageDao
import dev.nmgalo.core.model.messenger.MessageStatus
import dev.nmgalo.core.network.KatanaNetworkDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import dev.nmgalo.core.model.chat.Chat as ChatDomainModel
import dev.nmgalo.core.model.messenger.Message as MessageDomainModel

class FakeMessengerRepository @Inject constructor(
    @Dispatcher(KatanaDispatchers.IO)
    private val io: CoroutineDispatcher,
    @FakeImplementation
    private val dataSource: KatanaNetworkDataSource,
    private val messageDao: MessageDao,
    private val chatDao: ChatDao
) : MessengerRepository {

    override fun fetchNextChats(): Flow<Unit> = flow {
        dataSource.getChats().map { chat ->
            Chat(
                id = chat.id,
                lastUserId = chat.lastUserId,
                lastMessageId = chat.lastMessageId
            )
        }.apply { chatDao.insertAll(this) }
        emit(Unit)
    }.flowOn(io)

    override fun getAllChats(): Flow<List<ChatDomainModel>> = flow {
        emit(chatDao.getChats().map { result ->
            ChatDomainModel(
                id = result.chat.id ?: error("chatId can't be null"),
                userName = result.user.name,
                userProfilePicture = result.user.profilePicUrl,
                lastMessage = result.message.text
            )
        })
    }.flowOn(io)

    override fun fetchNextMessages(chatId: Long) = flow {
        val messages = dataSource.getConversation(chatId).map { data ->
            Message(
                id = data.id,
                chatId = data.chatId,
                text = data.message,
                userId = data.senderId,
                status = when (data.status) {
                    MESSAGE_STATUS_SENT -> MessageStatus.SENT
                    MESSAGE_STATUS_READ -> MessageStatus.READ
                    else -> MessageStatus.PENDING
                },
                createdAt = data.createdAt
            )
        }

        messageDao.insertAll(messages)

        emit(Unit)
    }.flowOn(io)

    override fun getAllByChatId(chatId: Long): Flow<List<MessageDomainModel>> = flow {
        emit(messageDao.getChatMessages(chatId).map {
            MessageDomainModel(
                id = it.id ?: -1,
                chatId = it.chatId,
                message = it.text,
                senderId = it.userId,
                status = it.status,
                createdAt = it.createdAt ?: -1
            )
        })
    }.flowOn(io)

    companion object {
        const val MESSAGE_STATUS_SENT = 2
        const val MESSAGE_STATUS_READ = 3
    }
}
