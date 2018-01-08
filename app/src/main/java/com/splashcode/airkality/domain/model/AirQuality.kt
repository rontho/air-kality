package com.splashcode.airkality.domain.model

data class AirQuality (val location: String, val quality: Int) {
    companion object {
        val NO_OP =  AirQuality("NO_LOCATION", -1)
    }
}
