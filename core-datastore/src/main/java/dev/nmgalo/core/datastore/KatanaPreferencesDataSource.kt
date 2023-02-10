package dev.nmgalo.core.datastore

import androidx.datastore.core.DataStore
import dev.nmgalo.core.model.data.UserData
import dev.nmgalo.core.model.data.UserLanguage
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class KatanaPreferencesDataSource @Inject constructor(
    private val userPreferences: DataStore<UserPreferences>
) {
    val userData = userPreferences.data.map {
        UserData(
            isDarkModeEnabled = it.isDarkModeEnabled,
            shouldShowOnboardingScreen = it.shouldShowOnboardingScreen,
            userLanguage = when (it.userLanguage) {
                Languages.EN_US -> UserLanguage.EN_US
                Languages.KA_GE -> UserLanguage.KA_GE
                else -> UserLanguage.EN_US
            }
        )
    }

    suspend fun setDarkModeEnabled(shouldUseDarkMode: Boolean) {
        userPreferences.updateData {
            it.copy {
                this.isDarkModeEnabled = shouldUseDarkMode
            }
        }
    }

    suspend fun setShouldShowOnboardingScreen(shouldShowOnboarding: Boolean) {
        userPreferences.updateData {
            it.copy {
                this.shouldShowOnboardingScreen = shouldShowOnboarding
            }
        }
    }
}
