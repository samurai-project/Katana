package dev.nmgalo.katana.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.nmgalo.core.data.userdata.UserDataRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository
) : ViewModel() {

    fun disableOnBoardingScreen() {
        viewModelScope.launch {
            userDataRepository.setShouldShowOnBoarding(false)
        }
    }
}
