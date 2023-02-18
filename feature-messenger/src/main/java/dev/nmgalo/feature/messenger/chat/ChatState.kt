package dev.nmgalo.feature.messenger.chat

import dev.nmgalo.feature.messenger.model.Message

sealed interface ChatState {

    object Loading: ChatState

    data class Success(val conversation: List<Message>): ChatState

    object Error: ChatState
}
