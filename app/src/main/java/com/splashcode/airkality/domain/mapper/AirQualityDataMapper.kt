package com.splashcode.airkality.domain.mapper

import com.splashcode.airkality.data.http.response.AirQualityResponse
import com.splashcode.airkality.domain.model.AirQuality

/**
 * AQI
 * 0–50	Excellent	No health implications.
 * 51–100	Good	Few hypersensitive individuals should reduce outdoor exercise.
 * 101–150	Lightly Polluted	Slight irritations may occur, individuals with breathing or heart problems should reduce outdoor exercise.
 * 151–200	Moderately Polluted	Slight irritations may occur, individuals with breathing or heart problems should reduce outdoor exercise.
 * 201–300	Heavily Polluted	Healthy people will be noticeably affected. People with breathing or heart problems will experience reduced endurance in activities. These individuals and elders should remain indoors and restrict activities.
 * 300+	Severely Polluted	Healthy people will experience reduced endurance in activities. There may be strong irritations and symptoms and may trigger other illnesses. Elders and the sick should remain indoors and avoid exercise. Healthy individuals should avoid outdoor activities.
 */
class AirQualityDataMapper {
    fun translateFromResponse(response: AirQualityResponse ?): AirQuality {
        return AirQuality(response?.data?.city, response?.data?.current?.pollution?.aqius)
    }
}