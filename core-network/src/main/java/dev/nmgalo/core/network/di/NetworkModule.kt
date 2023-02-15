package dev.nmgalo.core.network.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.nmgalo.core.common.di.FakeImplementation
import dev.nmgalo.core.network.KatanaNetworkClient
import dev.nmgalo.core.network.KatanaNetworkDataSource
import dev.nmgalo.core.network.fake.FakeAssetManager
import dev.nmgalo.core.network.fake.FakeKatanaNetworkApi
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {

    @Binds
    fun bindsKatanaNetwork(katanaNetworkClient: KatanaNetworkClient): KatanaNetworkDataSource

    @Binds
    @FakeImplementation
    fun bindsFakeKatanaNetwork(katanaNetworkClient: FakeKatanaNetworkApi): KatanaNetworkDataSource

    companion object {
        @Provides
        @Singleton
        fun providesNetworkJson(): Json = Json {
            ignoreUnknownKeys = true
        }

        @Provides
        @Singleton
        fun bindsFakeAssetManager(
            @ApplicationContext context: Context
        ) = FakeAssetManager(context.assets::open)
    }
}
