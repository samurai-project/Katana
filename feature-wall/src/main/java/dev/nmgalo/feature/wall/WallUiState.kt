package dev.nmgalo.feature.wall

import dev.nmgalo.core.model.wall.Wall

/**
 * sealed class representing wall state
 */
sealed interface WallUiState {

    /**
     * Loading state
     **/
    object Loading : WallUiState

    /**
     * Error state, e.g. network related issue, invalid JSON response.
     **/
    object Error : WallUiState

    /**
     * Success state, Everything is ok
     **/
    data class Success(
        val wall: List<Wall>
    ) : WallUiState
}
