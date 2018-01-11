package com.splashcode.airkality.presentation.airquality

import com.splashcode.airkality.R

interface AirQualityContract {

    interface View {
        fun bindViewModel(model: AirQualityViewModel)
    }

    interface Presenter<T : View> {
        fun init(view: AirQualityContract.View)
        fun getAirQuality()
    }
}

class AirQualityViewModel(val location: String,
                          val quality: String,
                          val type: AirQualityType)

enum class AirQualityType(val color: Int, val message: Int) {
    GOOD (R.color.good, R.string.good),
    MODERATE(R.color.moderate, R.string.moderate),
    SLIGHTLY_UNHEALTHY(R.color.slightly_unhealthy, R.string.slightly_unhealthy),
    UNHEALTHY(R.color.unhealthy, R.string.unhealthy),
    HIGHLY_UNHEALTHY(R.color.highly_unhealthy, R.string.highly_unhealthy),
    HAZARDOUS(R.color.hazardous, R.string.hazardous),
    UNKNOW(android.R.color.white, R.string.unknown)

}