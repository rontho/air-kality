package com.splashcode.airkality.data

import com.splashcode.airkality.data.http.request.AirQualityRequest
import com.splashcode.airkality.data.location.UserLocationProvider
import com.splashcode.airkality.domain.Datasource
import com.splashcode.airkality.domain.model.AirQuality

class AirQualityDatasource(val airQualityRequest: AirQualityRequest, val userLocationProvider: UserLocationProvider): Datasource {

    override fun getAirQuality(latitude: String, longitude:String): AirQuality {
        var result = AirQuality.NO_OP
        airQualityRequest.run(latitude, longitude)?.let {
            result = it.toDomainObject()
        }
        return result
    }

    override fun getCurrentLocation() = userLocationProvider.get()
}