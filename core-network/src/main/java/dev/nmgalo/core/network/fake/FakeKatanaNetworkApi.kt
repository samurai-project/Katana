package dev.nmgalo.core.network.fake

import dev.nmgalo.core.common.Dispatcher
import dev.nmgalo.core.common.KatanaDispatchers
import dev.nmgalo.core.network.KatanaNetworkDataSource
import dev.nmgalo.core.network.fake.model.MessageDTO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeKatanaNetworkApi @Inject constructor(
    @Dispatcher(KatanaDispatchers.IO)
    private val io: CoroutineDispatcher,
    private val networkJson: Json,
    private val fakeAssetManager: TestFakeAssetManager,
    private val katanaNetworkDataSource: KatanaNetworkDataSource
) : KatanaNetworkDataSource by katanaNetworkDataSource {

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getConversation(conversationId: Long): List<MessageDTO> =
        withContext(io) {
            fakeAssetManager.open(CONVERSATIONS).use(networkJson::decodeFromStream)
        }

    companion object {
        const val CONVERSATIONS = "conversation.json"
    }
}
