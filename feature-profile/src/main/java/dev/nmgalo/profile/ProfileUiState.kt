package dev.nmgalo.profile

import dev.nmgalo.core.model.user.User

sealed interface ProfileUiState {

    object Loading : ProfileUiState

    object Error : ProfileUiState

    data class Success(
        val profile: User
    ) : ProfileUiState
}
