package dev.nmgalo.core.data.userdata

import dev.nmgalo.core.datastore.KatanaPreferencesDataSource
import dev.nmgalo.core.model.data.UserData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OnDeviceUserDataRepository @Inject constructor(
    private val katanaPreferencesDataSource: KatanaPreferencesDataSource
) : UserDataRepository {
    override val userData: Flow<UserData> =
        katanaPreferencesDataSource.userData

    override suspend fun setDarkModeEnabled(darkModeEnabled: Boolean) =
        katanaPreferencesDataSource.setDarkModeEnabled(darkModeEnabled)

    override suspend fun setShouldShowOnBoarding(shouldShowOnBoarding: Boolean) =
        katanaPreferencesDataSource.setShouldShowOnboardingScreen(shouldShowOnBoarding)
}
