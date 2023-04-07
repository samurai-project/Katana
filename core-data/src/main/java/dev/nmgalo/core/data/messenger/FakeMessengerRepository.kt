package dev.nmgalo.core.data.messenger

import dev.nmgalo.core.common.Dispatcher
import dev.nmgalo.core.common.KatanaDispatchers
import dev.nmgalo.core.common.di.FakeImplementation
import dev.nmgalo.core.database.messenger.chat.Chat
import dev.nmgalo.core.database.messenger.chat.ChatDao
import dev.nmgalo.core.database.messenger.message.Message
import dev.nmgalo.core.database.messenger.message.MessageDao
import dev.nmgalo.core.database.messenger.user.User
import dev.nmgalo.core.database.messenger.user.UserDao
import dev.nmgalo.core.model.messenger.ChatUser
import dev.nmgalo.core.model.messenger.MessageStatus
import dev.nmgalo.core.network.messenger.MessengerNetworkDataSource
import dev.nmgalo.core.network.wall.WallNetworkDataSource
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
    private val dataSource: MessengerNetworkDataSource,
    private val messageDao: MessageDao,
    private val chatDao: ChatDao,
    private val userDao: UserDao,
) : MessengerRepository {

    override fun fetchNextChats(): Flow<Unit> = flow {
        val chats = dataSource.getChats().map { chat ->
            Chat(
                id = chat.id,
                lastUserId = chat.lastUserId,
                lastMessageId = chat.lastMessageId
            )
        }
        chatDao.insertAll(chats)
        emit(Unit)
    }.flowOn(io)

    override fun getAllChats(): Flow<List<ChatDomainModel>> = flow {
        emit(chatDao.getChats().map { result ->
            ChatDomainModel(
                id = result.chat.id ?: error("chatId can't be null"),
                userName = result.user.name,
                userProfilePicture = result.user.profilePicUrl,
                lastMessage = result.message?.text
            )
        })
    }.flowOn(io)

    override fun fetchChatUsers(chatId: Long): Flow<Unit> = flow {
        userDao.insertUsers(
            dataSource.getChatUsers(chatId).map { user ->
                User(
                    id = user.id,
                    name = user.name,
                    profilePicUrl = user.profilePicUrl
                )
            })
        emit(Unit)
    }.flowOn(io)

    override fun getChatUsers(userIds: IntArray): Flow<List<ChatUser>> = flow {
        emit(userDao.getUsersById(userIds).map { user ->
            ChatUser(
                id = user.id ?: error("userId can't be null"),
                name = user.name,
                profilePicUrl = user.profilePicUrl
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

    override fun getAllMessageByChatId(chatId: Long): Flow<List<MessageDomainModel>> = flow {
        emit(messageDao.getChatMessages(chatId).map {
            MessageDomainModel(
                id = it.message.id ?: error("messageId can't be null"),
                chatId = it.message.chatId,
                message = it.message.text,
                senderId = it.message.userId,
                status = it.message.status,
                createdAt = it.message.createdAt ?: -1,
                user = ChatUser(
                    id = it.user.id ?: error("userId can't be null"),
                    name = it.user.name,
                    profilePicUrl = it.user.profilePicUrl
                )
            )
        })
    }.flowOn(io)

    override fun sendMessage(chatId: Long, text: String) = flow {
        messageDao.insert(
            Message(
                userId = 1,
                chatId = chatId,
                text = text,
                status = MessageStatus.PENDING
            )
        )
        emit(Unit)
    }.flowOn(io)

    companion object {
        const val MESSAGE_STATUS_SENT = 2
        const val MESSAGE_STATUS_READ = 3
    }
}
