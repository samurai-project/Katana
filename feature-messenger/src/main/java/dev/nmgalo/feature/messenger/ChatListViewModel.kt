package dev.nmgalo.feature.messenger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.nmgalo.core.data.messenger.MessengerRepository
import dev.nmgalo.core.ui.STOP_TIMEOUT_MILLIS
import dev.nmgalo.feature.messenger.model.Chat
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatListViewModel @Inject constructor(
    private val messengerRepository: MessengerRepository
) : ViewModel() {

    fun fetchFakeData() {
        viewModelScope.launch {
            messengerRepository.fetchChatUsers(1).collect()
            messengerRepository.fetchNextChats().collect()
            messengerRepository.fetchNextMessages(1).collect()
        }
    }

    val chatListState: StateFlow<ChatListState> = messengerRepository.getAllChats().flatMapLatest {
        if (it.isEmpty()) flowOf(ChatListState.Empty)
        else flowOf(ChatListState.Success(it.map { chat ->
            Chat(
                id = chat.id,
                userName = chat.userName,
                userProfilePicture = chat.userProfilePicture,
                lastMessage = chat.lastMessage
            )
        }))
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MILLIS),
        initialValue = ChatListState.Loading
    )
}
