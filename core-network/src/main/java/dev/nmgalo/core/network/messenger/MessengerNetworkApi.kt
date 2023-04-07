package dev.nmgalo.core.network.messenger

import retrofit2.http.GET

interface MessengerNetworkApi {
    @GET
    fun dummy(): Unit
}
