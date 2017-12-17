package com.splashcode.airkality.data.http.response

data class AirQualityDataResponse(val city: String, val state: String, val country: String, val location: LocationResponse, val current: AirQualityCurrentResponse)