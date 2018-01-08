package com.splashcode.airkality.data

import com.splashcode.airkality.data.http.request.AirQualityRequest
import com.splashcode.airkality.domain.Datasource
import com.splashcode.airkality.domain.model.AirQuality

class AirQualityDatasource: Datasource {
    override fun getAirQuality(): AirQuality {
        var result = AirQuality.NO_OP
        AirQualityRequest().run()?.let {
            result = it.toDomainObject()
        }
        return result
    }
}