package dev.nmgalo.feature.wall

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.nmgalo.core.common.result.Result
import dev.nmgalo.core.common.result.asResult
import dev.nmgalo.core.data.wall.WallRepository
import dev.nmgalo.core.ui.STOP_TIMEOUT_MILLIS
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class WallViewModel @Inject constructor(
    wallRepository: WallRepository
) : ViewModel() {

    val wallState: StateFlow<WallUiState> = wallRepository.getWall()
        .asResult()
        .map {
            when (it) {
                is Result.Error -> WallUiState.Error(it.exception)
                Result.Loading -> WallUiState.Loading
                is Result.Success -> WallUiState.Success(it.data)
            }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MILLIS),
            initialValue = WallUiState.Loading
        )
}
