package dev.nmgalo.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.nmgalo.core.data.userdata.UserDataRepository
import dev.nmgalo.core.ui.STOP_TIMEOUT_MILLIS
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GeneralScreenViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository
) : ViewModel() {

    val isDarkModeEnabled = userDataRepository.userData.flatMapLatest {
        flowOf(it.isDarkModeEnabled)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MILLIS),
        initialValue = false
    )

    fun setDarkModeIsEnabled(shouldUseDarkMode: Boolean) {
        viewModelScope.launch {
            userDataRepository.setDarkModeEnabled(shouldUseDarkMode)
        }
    }
}
