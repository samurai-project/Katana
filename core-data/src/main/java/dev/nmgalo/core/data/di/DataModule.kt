package dev.nmgalo.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.nmgalo.core.data.user.UserRepository
import dev.nmgalo.core.data.user.DefaultUserRepository
import dev.nmgalo.core.data.userdata.OnDeviceUserDataRepository
import dev.nmgalo.core.data.userdata.UserDataRepository
import dev.nmgalo.core.data.wall.WallRepository
import dev.nmgalo.core.data.wall.DefaultWallRepository

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsWallRepository(wallRepository: DefaultWallRepository): WallRepository

    @Binds
    fun bindsUserRepository(userRepository: DefaultUserRepository): UserRepository

    @Binds
    fun bindsUserDataRepository(userDataRepository: OnDeviceUserDataRepository): UserDataRepository
}
