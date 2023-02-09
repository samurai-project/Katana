package dev.nmgalo.core.datastore

import androidx.datastore.core.DataStore
import dev.nmgalo.core.model.data.UserData
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class KatanaPreferencesDataSource @Inject constructor(
    private val userPreferences: DataStore<UserPreferences>
) {
    val userData = userPreferences.data.map {
        UserData(
            isDarkModeEnabled = it.isDarkModeEnabled,
            shouldShowOnboardingScreen = it.shouldShowOnboardingScreen
        )
    }

    suspend fun setDarkMode(shouldUseDarkMode: Boolean) {
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
