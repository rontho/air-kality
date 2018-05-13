package com.splashcode.airkality.domain

import com.splashcode.airkality.domain.model.AirQuality
import com.splashcode.airkality.domain.model.UserLocation

interface Datasource {
    fun getAirQuality(latitude: String, longitude: String): AirQuality
    fun getCurrentLocation(): UserLocation
}