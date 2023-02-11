package dev.nmgalo.core.database.messenger

import androidx.room.Embedded
import androidx.room.Relation
import dev.nmgalo.core.database.messenger.chat.Chat
import dev.nmgalo.core.database.messenger.message.Message

data class ChatAndMessage(
    @Embedded val chat: Chat,
    @Relation(
        parentColumn = "id",
        entityColumn = "chat_id"
    )
    val message: List<Message>
)
