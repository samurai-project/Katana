package dev.nmgalo.feature.wall

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.nmgalo.core.data.wall.WallRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WallViewModel @Inject constructor(
    wallRepository: WallRepository
) : ViewModel() {

    private val _wallState = MutableStateFlow<WallUiState>(WallUiState.Loading)
    val wallState: StateFlow<WallUiState> = _wallState

    init {
        viewModelScope.launch {
            wallRepository.getWall().collect {
                _wallState.tryEmit(WallUiState.Success(it))
            }
        }
    }

}
