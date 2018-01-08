package com.splashcode.airkality.presentation.infrastructure

import android.app.Application
import com.splashcode.airkality.data.AirQualityDatasource
import com.splashcode.airkality.domain.Datasource
import com.splashcode.airkality.domain.usecase.GetAirQualityUseCase
import com.splashcode.airkality.presentation.airquality.AirQualityPresenter
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext

class AirKalityApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        //start Koin
        startKoin(this, listOf(myModule))
    }
}

val myModule : Module = applicationContext {
    provide<Datasource> { AirQualityDatasource() }
    provide { GetAirQualityUseCase(get()) }
    provide { AirQualityPresenter(get()) }
}