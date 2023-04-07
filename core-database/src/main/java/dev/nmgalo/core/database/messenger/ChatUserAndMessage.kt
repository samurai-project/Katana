package dev.nmgalo.core.database.messenger

import androidx.room.Embedded
import androidx.room.Relation
import dev.nmgalo.core.database.messenger.chat.Chat
import dev.nmgalo.core.database.messenger.message.Message
import dev.nmgalo.core.database.messenger.user.User

data class ChatUserAndMessage(
    @Embedded val chat: Chat,

    @Relation(
        parentColumn = "last_user_id",
        entityColumn = "id"
    )
    val user: User,

    @Relation(
        parentColumn = "last_message_id",
        entityColumn = "id"
    )
    val message: Message?
)
