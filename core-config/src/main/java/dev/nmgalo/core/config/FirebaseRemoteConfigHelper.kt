package dev.nmgalo.core.config

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigValue
import javax.inject.Inject


class FirebaseRemoteConfigHelper @Inject constructor(
    private val firebaseRemoteConfig: FirebaseRemoteConfig
) : RemoteConfigHelper {

    override fun get(key: String): FirebaseRemoteConfigValue = firebaseRemoteConfig.getValue(key)

    override fun getBoolean(key: String): Boolean = firebaseRemoteConfig.getBoolean(key)
}
