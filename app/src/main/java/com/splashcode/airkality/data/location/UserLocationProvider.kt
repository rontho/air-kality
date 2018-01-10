package com.splashcode.airkality.data.location

import android.content.Context
import com.google.android.gms.location.LocationServices
import com.splashcode.airkality.domain.model.UserLocation

class UserLocationProvider(val context: Context) {
    fun get(): UserLocation {
        var userLocation = UserLocation()
        val locationManager = LocationServices.getFusedLocationProviderClient(context);

    try {

        locationManager?.lastLocation?.addOnSuccessListener {
                location -> location?.let {
                    userLocation = UserLocation(location.latitude.toString(), location.longitude.toString())
                }
            }

        } catch (e: SecurityException) {
            //do nothing
        }
        return userLocation
    }
}