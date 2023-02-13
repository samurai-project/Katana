package dev.nmgalo.core.database.messenger.chat

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chat")
data class Chat(
    @PrimaryKey
    val id: Long? = null,
    @ColumnInfo(name = "last_user_id")
    val lastUserId: Long,
    @ColumnInfo(name = "last_message_id")
    val lastMessageId: Long,
)
