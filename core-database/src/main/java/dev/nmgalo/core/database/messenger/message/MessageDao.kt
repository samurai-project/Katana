package dev.nmgalo.core.database.messenger.message

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MessageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(messages: List<Message>)

    @Query("SELECT * FROM message WHERE chat_id = :chatId ORDER BY id DESC")
    fun getChatMessages(chatId: Long): List<Message>
}
