package dev.nmgalo.core.database.messenger

import androidx.room.Embedded
import androidx.room.Relation
import dev.nmgalo.core.database.messenger.message.Message
import dev.nmgalo.core.database.messenger.user.User

data class MessageAndUser(
    @Embedded val message: Message,

    @Relation(
        parentColumn = "user_id",
        entityColumn = "id"
    )
    val user: User
)
