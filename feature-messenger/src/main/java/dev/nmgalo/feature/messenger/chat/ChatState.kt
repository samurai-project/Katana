package dev.nmgalo.feature.messenger.chat

import dev.nmgalo.feature.messenger.model.Message

sealed interface ChatState {

    data object Loading: ChatState

    data class Success(val conversation: List<Message>): ChatState

    data object Error: ChatState
}
