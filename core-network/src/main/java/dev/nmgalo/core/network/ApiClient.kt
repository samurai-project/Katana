package dev.nmgalo.core.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiClient @Inject constructor(
    json: Json
): KatanaNetworkDataSource {


    @OptIn(ExperimentalSerializationApi::class)
    private val networkApi = Retrofit.Builder()
        .baseUrl("")
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

    override suspend fun getWall(): List<String> = networkApi.getWall()
}
