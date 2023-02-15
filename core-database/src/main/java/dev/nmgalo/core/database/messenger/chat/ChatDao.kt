package dev.nmgalo.core.database.messenger.chat

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import dev.nmgalo.core.database.messenger.ChatUserAndMessage

@Dao
interface ChatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(chats: List<Chat>)

    @Transaction
    @Query("SELECT * FROM chat")
    fun getChats(): List<ChatUserAndMessage>
}
