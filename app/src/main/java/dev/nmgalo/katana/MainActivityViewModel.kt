package dev.nmgalo.katana

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.nmgalo.core.data.userdata.UserDataRepository
import dev.nmgalo.core.ui.STOP_TIMEOUT_MILLIS
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    userDataRepository: UserDataRepository
) : ViewModel() {

    init {
        // TODO Only for testing on physical devices, remove later
        viewModelScope.launch {
            userDataRepository.setShouldShowOnBoarding(true)
            userDataRepository.setDarkModeEnabled(false)
        }
    }

    val uiState: StateFlow<UserDataState> = userDataRepository.userData.flatMapLatest {
        flowOf(UserDataState.Success(it))
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MILLIS),
        initialValue = UserDataState.Loading
    )
}
