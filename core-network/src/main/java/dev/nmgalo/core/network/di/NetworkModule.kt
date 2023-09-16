package dev.nmgalo.core.network.di

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.nmgalo.core.network.common.ServerConfig
import dev.nmgalo.core.network.wall.WallNetworkApi
import dev.nmgalo.core.network.common.fake.FakeAssetManager
import dev.nmgalo.core.network.messenger.MessengerNetworkApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideServerConfig(): ServerConfig {
        return ServerConfig("https://jsonplaceholder.typicode.com/")
    }

    @Provides
    fun providesNetworkClient(
        jsonSerializer: Json,
        serverConfig: ServerConfig
    ): Retrofit = Retrofit.Builder().apply {
        baseUrl(serverConfig.baseURL)
        client(
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
        )
        addConverterFactory(jsonSerializer.asConverterFactory("application/json".toMediaType()))
    }.build()

    @Provides
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    fun providesFakeAssetManager(
        @ApplicationContext context: Context
    ) = FakeAssetManager(context.assets::open)

    @Provides
    fun providesWallApi(retrofit: Retrofit): WallNetworkApi = retrofit.create()

    @Provides
    fun providesMessengerApi(retrofit: Retrofit): MessengerNetworkApi = retrofit.create()
}
