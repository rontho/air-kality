package com.splashcode.airkality.presentation.infrastructure

import android.app.Activity
import android.app.Application
import android.widget.Toast
import com.splashcode.airkality.data.AirQualityDatasource
import com.splashcode.airkality.data.http.request.AirQualityRequest
import com.splashcode.airkality.data.location.UserLocationProvider
import com.splashcode.airkality.domain.Datasource
import com.splashcode.airkality.domain.usecase.GetAirQualityUseCase
import com.splashcode.airkality.presentation.airquality.AirQualityPresenter
import com.splashcode.airkality.presentation.airquality.FirstAirQualityPresenter
import com.splashcode.airkality.presentation.infrastructure.AirKalityApplication.Companion.API_KEY
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext

class AirKalityApplication : Application() {

    companion object {
        const val API_KEY = "WMR536jHMy8ESh5Pf"
    }

    override fun onCreate() {
        super.onCreate()

        //start Koin
        startKoin(this, listOf(myModule))
    }
}

val myModule : Module = applicationContext {
    provide<Datasource> { AirQualityDatasource(get(), get()) }
    provide { UserLocationProvider(get()) }
    provide { AirQualityRequest(API_KEY) }
    provide { FirstAirQualityPresenter(get(), get()) }
}

fun Activity.makeToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}