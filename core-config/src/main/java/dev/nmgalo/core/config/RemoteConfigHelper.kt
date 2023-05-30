package dev.nmgalo.core.config

import com.google.firebase.remoteconfig.FirebaseRemoteConfigValue

interface RemoteConfigHelper {
    fun get(key: String): FirebaseRemoteConfigValue

    fun getBoolean(key: String): Boolean
}
