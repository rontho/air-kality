package com.splashcode.airkality.presentation.airquality

import com.splashcode.airkality.R
import com.splashcode.airkality.domain.usecase.GetAirQualityUseCase


class AirQualityPresenter(private val getAirQualityUseCase: GetAirQualityUseCase) : AirQualityContract.Presenter<AirQualityContract.View> {

    lateinit var view: AirQualityContract.View

    override fun init(view: AirQualityContract.View) {
        this.view = view
    }

    override fun getAirQuality() {
        getAirQualityUseCase.execute( { airQuality ->
            airQuality.let {

                val colorResId = when (airQuality.quality) {
                    in 0..50 -> R.color.good
                    in 51..100 -> R.color.moderate
                    in 101..150 -> R.color.slightly_unhealthy
                    in 151..200 -> R.color.unhealthy
                    in 201..250 -> R.color.highly_unhealthy
                    in 251..300 -> R.color.hazardous
                    else -> android.R.color.white
                }

                val stringResId = when (airQuality.quality) {
                    in 0..50 -> R.string.good
                    in 51..100 -> R.string.moderate
                    in 101..150 -> R.string.slightly_unhealthy
                    in 151..200 -> R.string.unhealthy
                    in 201..250 -> R.string.highly_unhealthy
                    in 251..300 -> R.string.hazardous
                    else -> R.string.unknown
                }

                val airQualityViewModel = AirQualityViewModel(it.location, it.quality.toString(), colorResId, stringResId)
                view.bindViewModel(airQualityViewModel)
            }
        })
    }
}
