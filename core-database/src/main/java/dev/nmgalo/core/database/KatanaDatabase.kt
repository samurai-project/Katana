package dev.nmgalo.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.nmgalo.core.database.messenger.chat.Chat
import dev.nmgalo.core.database.messenger.chat.ChatDao
import dev.nmgalo.core.database.messenger.message.Message
import dev.nmgalo.core.database.messenger.message.MessageDao
import dev.nmgalo.core.database.messenger.user.User
import dev.nmgalo.core.database.messenger.user.UserDao

@Database(
    entities = [User::class, Chat::class, Message::class],
    version = 1
)
@TypeConverters(
    DateConverters::class
)
abstract class KatanaDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun chatDao(): ChatDao

    abstract fun messageDao(): MessageDao
}
