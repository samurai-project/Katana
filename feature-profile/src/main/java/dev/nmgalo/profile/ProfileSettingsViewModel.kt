package dev.nmgalo.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.nmgalo.core.common.Dispatcher
import dev.nmgalo.core.common.KatanaDispatchers
import dev.nmgalo.core.data.userdata.UserDataRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileSettingsViewModel @Inject constructor(
    @Dispatcher(KatanaDispatchers.IO) private val io: CoroutineDispatcher,
    private val userDataRepository: UserDataRepository
) : ViewModel() {

    fun setDarkModeIsEnabled(shouldUseDarkMode: Boolean) {
        viewModelScope.launch(io) {
            userDataRepository.setDarkModeEnabled(shouldUseDarkMode)
        }
    }
}
