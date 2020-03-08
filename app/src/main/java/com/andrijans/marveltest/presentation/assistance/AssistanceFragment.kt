package com.andrijans.marveltest.presentation.assistance

import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andrijans.marveltest.R
import com.andrijans.marveltest.domain.IResultThread
import com.andrijans.marveltest.domain.IWorkerThread
import com.andrijans.marveltest.presentation.Navigator
import com.andrijans.marveltest.presentation.common.util.NetworkUtil
import com.google.android.gms.location.LocationServices
import dagger.android.support.DaggerFragment
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_assistance.*
import java.util.*
import javax.inject.Inject

class AssistanceFragment : DaggerFragment(), AssistanceContract.View {


    @Inject
    lateinit var presenter: AssistanceContract.Presenter
    @Inject
    lateinit var workerThread: IWorkerThread
    @Inject
    lateinit var resultThread: IResultThread
    @Inject
    lateinit var navigator: Navigator
    @Inject
    lateinit var networkUtil: NetworkUtil

    companion object {
        const val permsRequestCode = 200
        val perms = arrayOf("android.permission.ACCESS_FINE_LOCATION")
        fun newInstance(): AssistanceFragment = AssistanceFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_assistance, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapView?.onCreate(savedInstanceState)
        presenter.onCreate()
        roadAssistanceBtn.setOnClickListener { presenter.callAssistanceButtonClicked() }
    }


    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    override fun onStart() {
        super.onStart()
        mapView?.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView?.onStop()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView?.onSaveInstanceState(outState)
    }

    override fun requestLocationPermissionIfNeeded() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && activity?.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(perms, permsRequestCode)
        } else {
            presenter.locationPermissionGranted()
        }
    }

    override fun getUserLocation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && activity?.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") == PackageManager.PERMISSION_GRANTED) {
            activity?.let {
                LocationServices.getFusedLocationProviderClient(it).lastLocation.addOnSuccessListener { location ->
                    location?.let {
                        presenter.userLocationReceived(location.latitude, location.longitude)
                    }
                }
            }

        }
    }

    override fun setLocationOnMap(latitude: Double, longitude: Double) {
        mapView?.addUserLocation(latitude, longitude)
    }


    override fun setUserAddress(latitude: Double, longitude: Double) {
        if (networkUtil.isNetworkAvailable()) {
            Observable.just(getGeocoderAddress(latitude, longitude))
                    .subscribeOn(workerThread.getScheduler())
                    .observeOn(resultThread.getScheduler())
                    .subscribe { address ->
                        locationValue.text = address?.getAddressLine(0)
                    }
        }
    }

    private fun getGeocoderAddress(latitude: Double, longitude: Double): Address? {
        var address: Address? = null
        val geoCoder = Geocoder(context, Locale("en"))
        val addresses = geoCoder.getFromLocation(latitude, longitude, 10)
        if (addresses.size > 0) {
            for (adr in addresses) {
                if (adr.locality != null && adr.locality.isNotEmpty()) {
                    address = adr
                    break
                }
            }
        }
        return address
    }

    override fun setUserGpsCoords(latitude: Double, longitude: Double) {
        latlngValue.text = getString(R.string.gps_coords, latitude, longitude)
    }

    override fun navigateToPhoneDialer(number: String) {
        activity.let {
            navigator.openPhoneDialer(it as Context, number)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == permsRequestCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                presenter.locationPermissionGranted()
            }
        }
    }


}