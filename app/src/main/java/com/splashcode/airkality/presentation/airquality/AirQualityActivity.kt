package com.splashcode.airkality.presentation.airquality

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.splashcode.airkality.R
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.backgroundColor
import org.koin.android.ext.android.inject

class AirQualityActivity : AppCompatActivity(), AirQualityContract.View {

    val presenter : AirQualityPresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeToRefresh.setOnRefreshListener { getAirQuality() }

        presenter.init(this)
        getAirQuality()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun bind(model: AirQualityViewModel) {
        model.apply {
            container.backgroundColor = ContextCompat.getColor(container.context, colorResId)
            userLocation.text = location
            airQualityValue.text = quality
            airQualityDesc.text = getString(informationMessage)
            swipeToRefresh.isRefreshing = false
        }
    }

    private fun getAirQuality() {
        presenter.getAirQuality()
    }
}
