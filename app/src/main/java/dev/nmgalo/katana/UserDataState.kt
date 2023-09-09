package dev.nmgalo.katana

import dev.nmgalo.core.model.data.UserData

sealed interface UserDataState {
    data object Loading : UserDataState

    data class Success(val userData: UserData) : UserDataState
}
