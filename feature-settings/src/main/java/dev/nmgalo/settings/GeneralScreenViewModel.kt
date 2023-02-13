package dev.nmgalo.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.nmgalo.core.data.userdata.UserDataRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GeneralScreenViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository
) : ViewModel() {

    fun setDarkModeIsEnabled(shouldUseDarkMode: Boolean) {
        viewModelScope.launch {
            userDataRepository.setDarkModeEnabled(shouldUseDarkMode)
        }
    }
}
