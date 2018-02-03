package com.splashcode.airkality

import com.nhaarman.mockito_kotlin.*
import com.splashcode.airkality.data.http.request.AirQualityRequest
import com.splashcode.airkality.data.http.response.AirQualityResponse
import com.splashcode.airkality.data.location.UserLocationProvider
import com.splashcode.airkality.domain.model.UserLocation
import com.splashcode.airkality.presentation.airquality.FirstAirQualityContract
import com.splashcode.airkality.presentation.airquality.FirstAirQualityPresenter
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

class AirQualityPresenterTest {

    var mockLocationProvider: UserLocationProvider = mock()
    var mockAirQualityRequest: AirQualityRequest = mock()
    var mockView: FirstAirQualityContract.View = mock()

    lateinit var sut: FirstAirQualityContract.Presenter<FirstAirQualityContract.View>

    @Before
    fun setup() {
        sut = FirstAirQualityPresenter(mockLocationProvider, mockAirQualityRequest)
        sut.init(mockView)
    }

    @Test
    fun mockingStubbingAndVerifying() {
        //init
        val expectedUserLocation = UserLocation("a latitude", "a longitude")
        whenever(mockLocationProvider.get()).thenReturn(expectedUserLocation)

        //run
        sut.getAirQuality()

        //verify
        verify(mockAirQualityRequest).run(expectedUserLocation.latitude, expectedUserLocation.longitude)
    }

    @Test
    fun assertion() {
        //init
        val stubAirQualityRequest: AirQualityRequest = mock() {
            on { run("user latitude", "user longitude") }.doReturn( AirQualityResponse("an air quality status", null) )
        }

        val airQualityResponse = stubAirQualityRequest.run("user latitude", "user longitude")

        //run
        sut = FirstAirQualityPresenter(mockLocationProvider, stubAirQualityRequest)
        sut.getAirQuality()

        //verify
        assertTrue(airQualityResponse?.status.equals("an air quality status"))
    }

    @Test
    fun argumentMatcher() {
        //init
        mockAirQualityRequest.run("user latitude", "user longitude")

        //run
        sut.getAirQuality()

        //verify
        verify(mockAirQualityRequest).run( argThat { isNotEmpty() }, argThat { isNotEmpty() })
    }

    @Test
    fun checkedArgument() {
        //init
        val expectedUserLocation = UserLocation("a latitude for test", "a longitude for test")
        whenever(mockLocationProvider.get()).thenReturn(expectedUserLocation)

        //run
        sut.getAirQuality()

        //verify
        verify(mockAirQualityRequest).run(
                check {
                    assertThat( it.length, equalTo("a latitude for test".length) )
                    assertThat(it, equalTo("a latitude for test"))
                },

                check {
                    assertThat(it, equalTo("a longitude for test"))
                }
        )
    }

    @Test
    fun argumentCaptor() {
        //init
        val expectedUserLocation = UserLocation("user latitude", "a longitude for test")
        whenever(mockLocationProvider.get()).thenReturn(expectedUserLocation)

        //run
        sut.getAirQuality()

        //verify
        argumentCaptor<String>().apply {
            verify(mockAirQualityRequest, times(1)).run(capture(), any())

            assertEquals(1, allValues.size)
            assertEquals("user latitude", firstValue)
        }
    }
}
