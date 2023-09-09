package dev.nmgalo.profile

import dev.nmgalo.core.model.user.User

sealed interface ProfileUiState {

    data object Loading : ProfileUiState

    data object Error : ProfileUiState

    data class Success(
        val profile: User
    ) : ProfileUiState
}
