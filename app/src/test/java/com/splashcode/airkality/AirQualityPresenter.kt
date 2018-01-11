package com.splashcode.airkality

import com.splashcode.airkality.domain.usecase.GetAirQualityUseCase
import com.splashcode.airkality.presentation.airquality.AirQualityPresenter
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AirQualityPresenterTest {

    @Mock
    lateinit var mockGetAirQualityUseCase: GetAirQualityUseCase

    @Before
    fun
    val sut: AirQualityPresenter(mockGetAirQualityUseCase)

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}
