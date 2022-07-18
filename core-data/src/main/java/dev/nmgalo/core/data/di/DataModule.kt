package dev.nmgalo.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.nmgalo.core.data.wall.WallRepository
import dev.nmgalo.core.data.wall.WallRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsWallRepository(wallRepository: WallRepositoryImpl): WallRepository
}
