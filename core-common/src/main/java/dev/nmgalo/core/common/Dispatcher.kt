package dev.nmgalo.core.common

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val katanaDispatchers: KatanaDispatchers)
