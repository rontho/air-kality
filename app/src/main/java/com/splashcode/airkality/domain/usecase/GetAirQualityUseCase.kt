package com.splashcode.airkality.domain.usecase

import com.splashcode.airkality.domain.Datasource
import com.splashcode.airkality.domain.model.AirQuality

class GetAirQualityUseCase(private val datasource: Datasource) : AbstractUseCase<AirQuality>() {

    fun execute(uiCallback: (AirQuality) -> Unit) {
        super.run( {

            //TODO locationProvider.getLocation
            datasource.getAirQuality()

        }, { airQuality ->
            uiCallback(airQuality)
        })
    }
}
