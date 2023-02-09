package dev.nmgalo.core.data.userdata

import dev.nmgalo.core.model.data.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {

    /**
     * Stream of [UserData]
     **/
    val userData: Flow<UserData>

    /**
     * Set if dark mode is available or not.
     **/
    suspend fun setDarkModeEnabled(darkModeEnabled: Boolean)
}
