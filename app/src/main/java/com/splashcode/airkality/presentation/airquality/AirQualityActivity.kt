package com.splashcode.airkality.presentation.airquality

import android.content.res.Resources
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
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

        presenter.init(this)
        presenter.getAirQuality()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun bind(model: AirQualityViewModel) {
        container.backgroundColor = ContextCompat.getColor(this, model.colorResId)
        userLocation.text = model.location
        airQualityValue.text = model.quality
        airQualityDesc.text = getString(model.informationMessage)
    }
}
