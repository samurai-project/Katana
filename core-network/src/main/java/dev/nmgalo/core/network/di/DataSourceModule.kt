package dev.nmgalo.core.network.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.nmgalo.core.common.di.FakeImplementation
import dev.nmgalo.core.network.messenger.FakeMessengerNetworkApi
import dev.nmgalo.core.network.messenger.MessengerNetworkClient
import dev.nmgalo.core.network.messenger.MessengerNetworkDataSource
import dev.nmgalo.core.network.wall.WallNetworkClient
import dev.nmgalo.core.network.wall.WallNetworkDataSource

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun bindsWallNetwork(wallNetworkDataSource: WallNetworkClient): WallNetworkDataSource

    @Binds
    fun bindsMessengerNetwork(messengerNetworkDataSource: MessengerNetworkClient): MessengerNetworkDataSource

    @Binds
    @FakeImplementation
    fun bindsFakeMessengerNetwork(katanaNetworkClient: FakeMessengerNetworkApi): MessengerNetworkDataSource
}
