package dev.nmgalo.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.nmgalo.core.data.user.UserRepository
import dev.nmgalo.core.ui.STOP_TIMEOUT_MILLIS
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class ProfileViewModel @Inject constructor(
    userRepository: UserRepository
) : ViewModel() {

    val user: StateFlow<ProfileUiState> = userRepository.getUserById(MOCK_USER_ID).flatMapLatest {
        flowOf(ProfileUiState.Success(it))
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MILLIS),
        initialValue = ProfileUiState.Loading
    )

    companion object {
        const val MOCK_USER_ID: Long = 1
    }
}
