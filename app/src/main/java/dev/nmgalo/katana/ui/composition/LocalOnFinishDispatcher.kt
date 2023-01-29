package dev.nmgalo.katana.ui.composition

import androidx.compose.runtime.compositionLocalOf

val LocalOnFinishDispatcher = compositionLocalOf<(() -> Unit)?> { null }
