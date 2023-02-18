package dev.nmgalo.feature.messenger.conversations

import dev.nmgalo.feature.messenger.model.Chat

sealed interface ChatListState {

    object Loading : ChatListState

    object Error : ChatListState

    object Empty : ChatListState

    data class Success(val data: List<Chat>) : ChatListState
}
