package dev.nmgalo.core.database.messenger.chat

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ChatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(chats: List<Chat>)

    @Query("SELECT * FROM chat")
    fun getChats(): List<Chat>
}
