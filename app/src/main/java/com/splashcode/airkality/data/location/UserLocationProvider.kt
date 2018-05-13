package com.splashcode.airkality.data.location

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.splashcode.airkality.domain.model.UserLocation
import io.reactivex.Observable
import io.reactivex.Single
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.doAsync
import kotlin.coroutines.experimental.suspendCoroutine

@Suppress("EXPERIMENTAL_FEATURE_WARNING")
class UserLocationProvider(val context: Context) {

    fun get(): UserLocation {
        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
        return userLocationRx(fusedLocationProviderClient).blockingGet()
    }

    @SuppressLint("MissingPermission")
    fun userLocationRx(fusedLocationProviderClient: FusedLocationProviderClient): Single<UserLocation> = Single.create { emitter ->

        fusedLocationProviderClient.lastLocation
                .addOnSuccessListener { emitter.onSuccess( UserLocation(it.latitude.toString(), it.longitude.toString()) ) }
                .addOnFailureListener { emitter.onError( Throwable("Unable to get location")) }
    }

    @SuppressLint("MissingPermission")
    suspend fun getLocationAsync(fusedLocationProviderClient: FusedLocationProviderClient?): UserLocation = suspendCoroutine { continuation ->

        fusedLocationProviderClient?.lastLocation?.addOnSuccessListener {
            location -> location?.let {
                val userLocation = UserLocation(location.latitude.toString(), location.longitude.toString())
                continuation.resume(userLocation)
            }
        }?.addOnFailureListener {
            continuation.resumeWithException(Throwable("Unable to get location exception"))
        }
    }
}
