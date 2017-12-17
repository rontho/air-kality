package com.splashcode.airkality.data.http.request

import android.util.Log
import com.google.gson.Gson
import com.splashcode.airkality.data.http.response.AirQualityResponse
import java.net.URL

/**
 * http://api.airvisual.com/v2/nearest_city?lat=43.641928&lon=4.754517&key=WMR536jHMy8ESh5Pf
 * Key WMR536jHMy8ESh5Pf
 */
class AirQualityRequest {
    companion object {
        @JvmStatic
        private val URL = "http://api.airvisual.com/v2/nearest_city?lat=43.641928&lon=4.754517&key=YOURKEYHERE"
    }

    fun run (): AirQualityResponse? {
        val response = URL(URL).readText()
        val airQualityResponse = Gson().fromJson(response, AirQualityResponse::class.java)
        return airQualityResponse
    }
}
