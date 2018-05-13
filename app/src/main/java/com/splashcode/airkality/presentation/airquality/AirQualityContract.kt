package com.splashcode.airkality.presentation.airquality

import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
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

enum class AirQualityType(val color: Int, @DrawableRes val backgroundDrawable: Int, @StringRes val message: Int) {
    GOOD(R.color.good_end, R.drawable.air_quality_good_background, R.string.good),
    MODERATE(R.color.moderate_end, R.drawable.air_quality_moderate_background, R.string.moderate),
    SLIGHTLY_UNHEALTHY(R.color.slightly_unhealty_end, R.drawable.air_quality_slightly_unhealthy_background, R.string.slightly_unhealthy),
    UNHEALTHY(R.color.unhealthy_end, R.drawable.air_quality_unhealthy_background, R.string.unhealthy),
    HIGHLY_UNHEALTHY(R.color.highly_unhealthy_end, R.drawable.air_quality_highly_unhealthy_background, R.string.highly_unhealthy),
    HAZARDOUS(R.color.hazardous_end, R.drawable.air_quality_hazardous_background, R.string.hazardous),
    UNKNOW(android.R.color.white, R.drawable.air_quality_unknown_background, R.string.unknown)
}