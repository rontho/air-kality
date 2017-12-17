package com.splashcode.airkality.domain

import com.splashcode.airkality.data.http.request.AirQualityRequest
import com.splashcode.airkality.domain.mapper.AirQualityDataMapper
import com.splashcode.airkality.domain.model.AirQuality

class GetAirQualityUseCase : UseCase<AirQuality?>{
    override fun execute(): AirQuality? {
        val response = AirQualityRequest().run()
        return AirQualityDataMapper().translateFromResponse(response)
    }
}