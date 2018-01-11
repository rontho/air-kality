package com.splashcode.airkality.domain.model

import com.splashcode.airkality.presentation.airquality.AirQualityType

data class AirQuality (val location: String, val quality: Int, val type: AirQualityType) {
    companion object {
        val NO_OP =  AirQuality("NO_LOCATION", -1, AirQualityType.UNKNOW)
    }
}
