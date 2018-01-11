package com.splashcode.airkality.presentation.airquality

import com.splashcode.airkality.domain.usecase.GetAirQualityUseCase

class AirQualityPresenter(private val getAirQualityUseCase: GetAirQualityUseCase) : AirQualityContract.Presenter<AirQualityContract.View> {

    lateinit var view: AirQualityContract.View

    override fun init(view: AirQualityContract.View) {
        this.view = view
    }

    override fun getAirQuality() {
        getAirQualityUseCase.execute( { airQuality ->
            airQuality.let {
                val airQualityViewModel = AirQualityViewModel(it.location, it.quality.toString(), it.type)
                view.bindViewModel(airQualityViewModel)
            }
        })
    }
}