package dev.nmgalo.feature.messenger.chat

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.nmgalo.core.data.messenger.MessengerRepository
import dev.nmgalo.core.ui.STOP_TIMEOUT_MILLIS
import dev.nmgalo.feature.messenger.model.Message
import dev.nmgalo.feature.messenger.model.User
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import dev.nmgalo.core.model.messenger.Message as MessageModel

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val messageRepository: MessengerRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val chatId =
        savedStateHandle.get<Long>("chatId") ?: error("argument `chatId` is required")

    val chatState = messageRepository.getAllMessageByChatId(chatId = chatId)
        .flatMapLatest {
            flowOf(ChatState.Success(it.mapToUiModel()))
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MILLIS),
            initialValue = ChatState.Loading
        )

    fun sendMessage(message: String) {
        viewModelScope.launch {
            messageRepository.sendMessage(chatId, message).collect()
        }
    }

    private fun List<MessageModel>.mapToUiModel() = this.map { message ->
        Message(
            id = message.id,
            message = message.message,
            isMe = message.senderId == 1L,
            user = User(
                id = message.user.id,
                name = message.user.name,
                profilePicUrl = message.user.profilePicUrl
            )
        )
    }
}
