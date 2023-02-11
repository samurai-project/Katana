package dev.nmgalo.core.database.messenger.chat

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import dev.nmgalo.core.database.messenger.ChatAndMessage

@Dao
interface ChatDao {

    @Transaction
    @Query("SELECT * FROM chat WHERE id = :chatId")
    fun getChatMessages(chatId: Long): List<ChatAndMessage>
}
