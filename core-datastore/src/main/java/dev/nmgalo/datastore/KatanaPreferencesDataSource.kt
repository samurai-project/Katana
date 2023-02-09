package dev.nmgalo.datastore

import androidx.datastore.core.DataStore
import dev.nmgalo.core.datastore.UserPreferencesOuterClass.UserPreferences
import dev.nmgalo.core.model.data.UserData
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class KatanaPreferencesDataSource @Inject constructor(
    private val userPreferences: DataStore<UserPreferences>
) {
    val userData = userPreferences.data.map {
        UserData(
            isDarkModeEnabled = it.isDarkModeEnabled
        )
    }
}
