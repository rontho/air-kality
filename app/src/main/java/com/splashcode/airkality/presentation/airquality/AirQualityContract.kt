package com.splashcode.airkality.presentation.airquality

import android.support.annotation.ColorRes
import android.support.annotation.StringRes

interface AirQualityContract {

    interface View {
        fun bind(model: AirQualityViewModel)
    }

    interface Presenter<T : View> {
        fun init(view: AirQualityContract.View)
        fun getAirQuality()
    }
}

class AirQualityViewModel(val location: String,
                          val quality: String,
                          @ColorRes val colorResId: Int,
                          @StringRes val informationMessage: Int)