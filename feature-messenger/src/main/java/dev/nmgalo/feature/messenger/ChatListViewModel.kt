package dev.nmgalo.feature.messenger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.nmgalo.core.data.messenger.MessengerRepository
import dev.nmgalo.core.ui.STOP_TIMEOUT_MILLIS
import dev.nmgalo.feature.messenger.model.Chat
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ChatListViewModel @Inject constructor(
    messengerRepository: MessengerRepository
) : ViewModel() {

    val chatListState: StateFlow<ChatListState> = messengerRepository.getAllChats().flatMapLatest {
        flowOf(ChatListState.Success(it.map { chat ->
            Chat(
                id = chat.id,
                lastUserId = chat.lastUserId,
                lastMessageId = chat.lastMessageId
            )
        }))
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MILLIS),
        initialValue = ChatListState.Loading
    )
}
