package com.splashcode.airkality.data.http.response

import com.splashcode.airkality.domain.model.AirQuality
import com.splashcode.airkality.presentation.airquality.AirQualityType

data class AirQualityResponse(val status: String, val data: AirQualityDataResponse?) {

    fun toDomainObject(): AirQuality {
        var result = AirQuality.NO_OP
        data?.let {

            val airQuality = it.current.pollution.aqius

            val type = when (airQuality) {
                in 0..50 -> AirQualityType.GOOD
                in 51..100 -> AirQualityType.MODERATE
                in 101..150 -> AirQualityType.SLIGHTLY_UNHEALTHY
                in 151..200 -> AirQualityType.UNHEALTHY
                in 201..250 -> AirQualityType.HIGHLY_UNHEALTHY
                in 251..300 -> AirQualityType.HAZARDOUS
                else -> AirQualityType.UNKNOW
            }

            result = AirQuality(it.city, it.current.pollution.aqius, type)
        }
        return result
    }
}