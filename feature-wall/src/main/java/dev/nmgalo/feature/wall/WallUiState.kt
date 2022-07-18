package dev.nmgalo.feature.wall

import dev.nmgalo.core.model.wall.Wall

sealed interface WallUiState {
    object Loading : WallUiState

    data class Success(
        val wall: List<Wall>
    ) : WallUiState
}
