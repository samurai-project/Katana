package dev.nmgalo.core.database.messenger

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.nmgalo.core.database.KatanaDatabase
import dev.nmgalo.core.database.messenger.user.UserDao

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideKatanaDatabase(
        @ApplicationContext context: Context,
    ): KatanaDatabase = Room.databaseBuilder(
        context,
        KatanaDatabase::class.java,
        "katana.db"
    ).build()

    @Provides
    fun provideUserDao(
        katanaDatabase: KatanaDatabase
    ): UserDao = katanaDatabase.userDao()
}
