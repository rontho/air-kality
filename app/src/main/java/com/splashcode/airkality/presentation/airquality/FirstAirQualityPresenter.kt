package com.splashcode.airkality.presentation.airquality

import com.splashcode.airkality.data.http.request.AirQualityRequest
import com.splashcode.airkality.data.location.UserLocationProvider
import org.jetbrains.anko.doAsync

class FirstAirQualityPresenter(val userLocationProvider: UserLocationProvider, val airQualityRequest: AirQualityRequest) : FirstAirQualityContract.Presenter<FirstAirQualityContract.View> {

    lateinit var view: FirstAirQualityContract.View

    override fun init(view: FirstAirQualityContract.View) {
        this.view = view
    }

    override fun getAirQuality() {
        doAsync {

            val userLocation = userLocationProvider.get()
            val airQualityResponse = airQualityRequest.run(userLocation.latitude, userLocation.longitude)

            val airQuality = airQualityResponse?.toDomainObject()

            airQuality?.let {
                val airQualityViewModel = AirQualityViewModel(it.location, it.quality.toString(), it.type)
                view.bindViewModel(airQualityViewModel)
            }
        }
    }

    override fun getAirQualityLambda() {
        doAsync {

            val userLocation = userLocationProvider.get()
            val airQuality = airQualityRequest.runAndGetDomainObject(userLocation.latitude, userLocation.longitude, { airQualityResponse -> airQualityResponse.toDomainObject() } )

            airQuality?.let {
                val airQualityViewModel = AirQualityViewModel(it.location, it.quality.toString(), it.type)
                view.bindViewModel(airQualityViewModel)
            }
        }
    }
}

interface FirstAirQualityContract {

    interface View {
        fun bindViewModel(model: AirQualityViewModel)
    }

    interface Presenter<T : View> {
        fun init(view: FirstAirQualityContract.View)
        fun getAirQuality()
        fun getAirQualityLambda()
    }
}