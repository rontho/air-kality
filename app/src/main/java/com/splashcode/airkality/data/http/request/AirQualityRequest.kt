package com.splashcode.airkality.data.http.request

import com.google.gson.Gson
import com.splashcode.airkality.data.http.response.AirQualityResponse
import java.net.URL

class AirQualityRequest {
    companion object {
        @JvmStatic
        private val URL = "http://api.airvisual.com/v2/nearest_city?lat=43.525275&lon=5.335318&key=WMR536jHMy8ESh5Pf"
    }

    fun run (): AirQualityResponse? {
        val response = URL(URL).readText()
        val airQualityResponse = Gson().fromJson(response, AirQualityResponse::class.java)
        return airQualityResponse
    }
}
