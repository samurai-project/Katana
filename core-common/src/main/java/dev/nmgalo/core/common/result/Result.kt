package dev.nmgalo.core.common.result


sealed interface Result<out T> {

    data class Success<T>(val data: T) : Result<T>

    data object Loading : Result<Nothing>

    data class Error(val exception: Throwable? = null) : Result<Nothing>
}
