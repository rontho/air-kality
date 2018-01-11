package com.splashcode.airkality.data.location

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.LocationServices
import com.splashcode.airkality.domain.model.UserLocation

class UserLocationProvider(val context: Context) {

    @SuppressLint("MissingPermission")
    fun get(): UserLocation {
        var userLocation = UserLocation()
        val locationManager = LocationServices.getFusedLocationProviderClient(context)

        locationManager?.lastLocation?.addOnSuccessListener {
                location -> location?.let {
                    userLocation = UserLocation(location.latitude.toString(), location.longitude.toString())
                }
            }


        return userLocation
    }
}