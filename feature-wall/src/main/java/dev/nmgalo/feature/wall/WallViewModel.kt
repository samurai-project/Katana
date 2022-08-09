package dev.nmgalo.feature.wall

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.nmgalo.core.data.wall.WallRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class WallViewModel @Inject constructor(
    wallRepository: WallRepository
) : ViewModel() {

    val wallState: StateFlow<WallUiState> = wallRepository.getWall()
        .flatMapLatest {
            if (it.isNotEmpty()) {
                flowOf(WallUiState.Success(it))
            } else
                flowOf(WallUiState.Error)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MILLIS),
            initialValue = WallUiState.Loading
        )

    companion object {
        const val STOP_TIMEOUT_MILLIS: Long = 5_000
    }
}
