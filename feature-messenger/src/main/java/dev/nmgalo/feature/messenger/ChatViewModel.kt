package dev.nmgalo.feature.messenger

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.nmgalo.core.data.messenger.MessengerRepository
import dev.nmgalo.core.ui.STOP_TIMEOUT_MILLIS
import dev.nmgalo.feature.messenger.model.Message
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val messageRepository: MessengerRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val chatId =
        savedStateHandle.get<Long>("chatId") ?: error("argument `chatId` is required")

    val conversationState = messageRepository.getAllByChatId(chatId = chatId)
        .flatMapLatest {
            flowOf(ConversationState.Success(it.map { message ->
                Message(
                    id = message.id,
                    message = message.message,
                    isMe = message.senderId == 1L
                )
            }))
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MILLIS),
            initialValue = ConversationState.Loading
        )

    fun sendMessage(message: String) {
        viewModelScope.launch {
            messageRepository.sendMessage(message)
        }
    }
}
