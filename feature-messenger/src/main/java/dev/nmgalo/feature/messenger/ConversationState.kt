package dev.nmgalo.feature.messenger

import dev.nmgalo.feature.messenger.model.Message

sealed interface ConversationState {

    object Loading: ConversationState

    data class Success(val conversation: List<Message>): ConversationState

    object Error: ConversationState
}
