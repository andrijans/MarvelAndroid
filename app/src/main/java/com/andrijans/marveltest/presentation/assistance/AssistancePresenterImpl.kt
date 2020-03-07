package com.andrijans.marveltest.presentation.assistance

import com.andrijans.marveltest.domain.ILogger

class AssistancePresenterImpl(val view: AssistanceContract.View, val logger: ILogger) : AssistanceContract.Presenter {
    override fun onCreate() {
        view.requestLocationPermissionIfNeeded()
    }

    override fun locationPermissionGranted() {
        view.getUserLocation()
    }

    override fun userLocationReceived(latitude: Double, longitude: Double) {
        view.setLocationOnMap(latitude, longitude)
        view.setUserAddress(latitude, longitude)
        view.setUserGpsCoords(latitude, longitude)
    }

    override fun callAssistanceButtonClicked() {
        view.navigateToPhoneDialer("12345678")
    }
}