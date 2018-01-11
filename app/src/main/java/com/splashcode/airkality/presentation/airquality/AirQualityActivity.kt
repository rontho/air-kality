package com.splashcode.airkality.presentation.airquality

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.splashcode.airkality.R
import com.splashcode.airkality.presentation.infrastructure.makeToast
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.backgroundColor
import org.koin.android.ext.android.inject


class AirQualityActivity : AppCompatActivity(), AirQualityContract.View {

    val presenter : AirQualityPresenter by inject()

    companion object {
       const val PERMISSIONS_REQUEST_LOCATION = 123
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeToRefresh.setOnRefreshListener { getAirQuality() }

        presenter.init(this)
        getAirQuality()
    }


    override fun bindViewModel(model: AirQualityViewModel) {
        model.apply {
            container.backgroundColor = ContextCompat.getColor(container.context, colorResId)
            userLocation.text = location
            airQualityValue.text = quality
            airQualityDesc.text = getString(informationMessage)
            swipeToRefresh.isRefreshing = false
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if(requestCode == PERMISSIONS_REQUEST_LOCATION) {
             if(permissions.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                 presenter.getAirQuality()
             } else {
                 makeToast("You need to accept the location permissions")
                 swipeToRefresh.isRefreshing = false
             }
        }
    }

    private fun checkPermissions(doOnSuccess: () -> Unit) {
        val permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
        if(permissionCheck == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), PERMISSIONS_REQUEST_LOCATION)
        } else {
            doOnSuccess()
        }
    }

    private fun getAirQuality() {
        checkPermissions( { presenter.getAirQuality() })
    }
}
