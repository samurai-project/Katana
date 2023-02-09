package dev.nmgalo.feature.wall

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.nmgalo.core.data.wall.WallRepository
import dev.nmgalo.core.ui.STOP_TIMEOUT_MILLIS
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class WallViewModel @Inject constructor(
    wallRepository: WallRepository
) : ViewModel() {

    val wallState: StateFlow<WallUiState> = wallRepository.getWall()
        .flatMapLatest {
            if (it.isNotEmpty()) {
                flowOf(WallUiState.Success(it))
            } else flowOf(WallUiState.Error)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MILLIS),
            initialValue = WallUiState.Loading
        )
}
