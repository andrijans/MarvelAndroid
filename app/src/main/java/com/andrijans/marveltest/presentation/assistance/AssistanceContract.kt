package com.andrijans.marveltest.presentation.assistance

class AssistanceContract {
    interface View{
        fun requestLocationPermissionIfNeeded()
        fun getUserLocation()
        fun setLocationOnMap(latitude:Double,longitude:Double)
        fun setUserAddress(latitude:Double,longitude:Double)
        fun setUserGpsCoords(latitude:Double,longitude:Double)
        fun navigateToPhoneDialer(number:String)
    }
    interface Presenter{
        fun onCreate()
        fun locationPermissionGranted()
        fun userLocationReceived(latitude: Double,longitude: Double)
        fun callAssistanceButtonClicked()
    }
}