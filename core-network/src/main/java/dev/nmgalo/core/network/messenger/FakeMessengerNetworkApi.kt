package dev.nmgalo.core.network.messenger

import dev.nmgalo.core.common.Dispatcher
import dev.nmgalo.core.common.KatanaDispatchers
import dev.nmgalo.core.network.common.fake.TestFakeAssetManager
import dev.nmgalo.core.network.messenger.model.ChatDTO
import dev.nmgalo.core.network.messenger.model.ChatUsersDTO
import dev.nmgalo.core.network.messenger.model.MessageDTO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeMessengerNetworkApi @Inject constructor(
    @Dispatcher(KatanaDispatchers.IO)
    private val io: CoroutineDispatcher,
    private val json: Json,
    private val fakeAssetManager: TestFakeAssetManager,
    private val messengerNetworkDataSource: MessengerNetworkDataSource
) : MessengerNetworkDataSource by messengerNetworkDataSource {

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getConversation(conversationId: Long): List<MessageDTO> =
        withContext(io) {
            fakeAssetManager.open(CONVERSATIONS).use(json::decodeFromStream)
        }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getChats(): List<ChatDTO> = withContext(io) {
        fakeAssetManager.open(CHATS).use(json::decodeFromStream)
    }

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getChatUsers(chatId: Long): List<ChatUsersDTO> =
        withContext(io) {
            fakeAssetManager.open(USERS).use(json::decodeFromStream)
        }

    companion object {
        const val CONVERSATIONS = "conversation.json"
        const val CHATS = "chats.json"
        const val USERS = "users.json"
    }
}
