package dev.nmgalo.core.config

import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface FirebaseConfigModule {

    @Binds
    fun provideFirebaseRemoteConfigHelper(
        firebaseRemoteConfigHelper: FirebaseRemoteConfigHelper
    ): RemoteConfigHelper

    companion object {
        @Provides
        @Singleton
        fun provideFirebaseAnalytics(): FirebaseRemoteConfig {
            return Firebase.remoteConfig
        }
    }
}
