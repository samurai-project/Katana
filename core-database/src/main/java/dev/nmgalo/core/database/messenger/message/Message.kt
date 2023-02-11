package dev.nmgalo.core.database.messenger.message

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.nmgalo.core.model.messenger.MessageStatus
import java.sql.Date

@Entity(tableName = "message")
data class Message(
    @PrimaryKey
    val id: Long? = null,
    @ColumnInfo(name = "user_id")
    val userId: Long,
    @ColumnInfo(name = "chat_id")
    val chatId: Long,
    @ColumnInfo(name = "text")
    val text: String,
    @ColumnInfo(name = "status")
    val status: MessageStatus,
    @ColumnInfo(name = "created_at")
    val createdAt: Date
)
