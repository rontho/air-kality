package com.splashcode.airkality.domain

import com.splashcode.airkality.domain.model.AirQuality

interface Datasource {
    fun getAirQuality(): AirQuality
}