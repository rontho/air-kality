package com.splashcode.airkality.data.http.response

import com.splashcode.airkality.domain.model.AirQuality

data class AirQualityResponse(val status: String, val data: AirQualityDataResponse?) {

    fun toDomainObject(): AirQuality {
        var result = AirQuality.NO_OP
        data?.let {
            result = AirQuality(it.city, it.current.pollution.aqius)
        }
        return result
    }
}