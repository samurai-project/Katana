package dev.nmgalo.core.database.messenger.message

import androidx.room.Dao
import androidx.room.Query

@Dao
interface MessageDao {

    @Query("SELECT * FROM message")
    fun getAllMessage(): List<Message>
}
