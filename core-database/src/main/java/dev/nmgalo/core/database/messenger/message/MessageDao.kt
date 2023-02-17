package dev.nmgalo.core.database.messenger.message

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import dev.nmgalo.core.database.messenger.MessageAndUser

@Dao
interface MessageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(messages: List<Message>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(message: Message)

    @Transaction
    @Query("SELECT * FROM message WHERE chat_id = :chatId ORDER BY id DESC")
    fun getChatMessages(chatId: Long): List<MessageAndUser>
}
