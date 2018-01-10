package com.splashcode.airkality.data.http.request

import com.google.gson.Gson
import com.splashcode.airkality.data.http.response.AirQualityResponse
import java.net.URL

class AirQualityRequest(val apiKey: String) {
    companion object {
        @JvmStatic
        private val BASE_URL = "http://api.airvisual.com/v2/nearest_city?"
    }

    fun run(latitude: String, longitude: String): AirQualityResponse? {
        val response = URL(BASE_URL + "lat=${latitude}&lon=${longitude}&key=${apiKey}").readText()
        val airQualityResponse = Gson().fromJson(response, AirQualityResponse::class.java)
        return airQualityResponse
    }
}
