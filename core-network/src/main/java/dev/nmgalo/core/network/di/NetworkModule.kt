package dev.nmgalo.core.network.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.nmgalo.core.network.KatanaNetworkApi
import dev.nmgalo.core.network.KatanaNetworkDataSource
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Binds
    @Singleton
    abstract fun bindsKatanaNetwork(katanaNetwork: KatanaNetworkApi): KatanaNetworkDataSource

    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }
}
