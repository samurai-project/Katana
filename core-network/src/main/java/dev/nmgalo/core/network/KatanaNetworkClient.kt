package dev.nmgalo.core.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dev.nmgalo.core.network.fake.model.SendMessageDTO
import dev.nmgalo.core.network.model.users.UserDTO
import dev.nmgalo.core.network.model.wall.WallDTO
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KatanaNetworkClient @Inject constructor(json: Json) : KatanaNetworkDataSource {

    @OptIn(ExperimentalSerializationApi::class)
    private val networkApi = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .client(
            OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor().apply {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    }
                )
                .build()
        )
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build()
        .create(KatanaNetworkApi::class.java)

    override suspend fun getWall(): List<WallDTO> =
        networkApi.getWall()

    override suspend fun getUserById(userId: Long): UserDTO = networkApi.getUserById(userId)

    override suspend fun getAllUser(): List<UserDTO> = networkApi.getAllUser()

    override suspend fun getConversation(conversationId: Long) = TODO("Not yet implemented")

    override suspend fun getChats() = TODO("Not yet implemented")

    override suspend fun sendMessage(message: SendMessageDTO) = TODO("Not yet implemented")
}
