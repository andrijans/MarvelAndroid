package com.andrijans.marveltest.presentation.common.view.map

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.location.Location
import android.os.Build
import android.util.AttributeSet
import androidx.core.content.res.ResourcesCompat
import com.andrijans.marveltest.R
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*


class AssistanceMapView : MapView {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var map: GoogleMap? = null

    private val oneTimeListeners = mutableListOf<OnMapReadyCallback>()

    private val mapPaddingTop = resources.getDimensionPixelSize(R.dimen.map_padding_top)
    private val mapPaddingHorizontal = resources.getDimensionPixelSize(R.dimen.map_padding_horizontal)
    private var mapPaddingBottomBase = resources.getDimensionPixelSize(R.dimen.map_padding_bottom_default)


    private val onMapReadyCallback by lazy {
        OnMapReadyCallback {
            configureMap(it)
            map = it
            oneTimeListeners.forEach { listener ->
                listener.onMapReady(map)
            }
            oneTimeListeners.clear()
        }
    }


    fun moveMarkerToCenter(location: Location): Boolean {
        val position = LatLng(location.latitude, location.longitude)
        val projection = map?.projection
        val screenLocation = projection?.toScreenLocation(position)
        return if (screenLocation != null) {
            val scrollX = screenLocation.x - width.toFloat() / 2
            val scrollY = screenLocation.y - (height.toFloat() - resources.getDimensionPixelSize(R.dimen.bottom_view_height)) * 1 / 2
            map?.animateCamera(CameraUpdateFactory.scrollBy(scrollX, scrollY))
            true
        } else {
            false
        }
    }

    init {
        getMapAsync(onMapReadyCallback)
    }

    companion object {
        private const val LOCATION_ZOOM = 15.0f
//        private const val DEFAULT_MAX_MAP_ZOOM = 18.0f

        private const val HIGHLIGHTED_LOCATION_INDEX = -1

    }

    private fun configureMap(map: GoogleMap) {
        map.setPadding(
                mapPaddingHorizontal,
                mapPaddingTop,
                mapPaddingHorizontal,
                mapPaddingBottomBase
        )

        map.mapType = GoogleMap.MAP_TYPE_NORMAL
        map.isIndoorEnabled = false
        map.isBuildingsEnabled = false
        map.isTrafficEnabled = false

//        map.setMaxZoomPreference(DEFAULT_MAX_MAP_ZOOM)

        map.uiSettings?.apply {
            isCompassEnabled = false
            isIndoorLevelPickerEnabled = false
            isMapToolbarEnabled = false
            isMyLocationButtonEnabled = false
            isRotateGesturesEnabled = false
            isTiltGesturesEnabled = false
            isZoomControlsEnabled = false
        }


    }


    fun setLocation(latitude: Double?, longitude: Double?) {

        getMap(OnMapReadyCallback {
            moveCameraToLocation(it, latitude, longitude, LOCATION_ZOOM)
        })
    }


    fun moveToLocation(location: Location) {
        getMap(OnMapReadyCallback {
            moveCameraToLocation(it, location.latitude, location.longitude, LOCATION_ZOOM)
        })
    }

    private fun moveCameraToLocation(map: GoogleMap, latitude: Double?, longitude: Double?, zoom: Float?) {
        if (latitude != null && longitude != null && zoom != null) {
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(latitude, longitude), zoom))
        }
    }

    fun showUserLocation() {
        getMap(OnMapReadyCallback {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") == PackageManager.PERMISSION_GRANTED) {
                it.isMyLocationEnabled = true
                LocationServices.getFusedLocationProviderClient(context).lastLocation.addOnSuccessListener { location ->
                    if (location != null) {
                        setLocation(location.latitude, location.longitude)
                    }
                }
            }
        })
    }

    fun addUserLocation(latitude: Double, longitude: Double) {
        setLocation(latitude, longitude)
        drawMarker(latitude, longitude)

    }

    private fun drawMarker(latitude: Double, longitude: Double) {
        val circleDrawable = ResourcesCompat.getDrawable(resources, R.drawable.ic_person_pin, null)
        circleDrawable?.also {
            val markerIcon = getMarkerIconFromDrawable(it)
            map?.addMarker(MarkerOptions()
                    .position(LatLng(latitude, longitude))
                    .icon(markerIcon)
            )
        }

    }

    private fun getMarkerIconFromDrawable(drawable: Drawable): BitmapDescriptor {
        val canvas = Canvas()
        val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        canvas.setBitmap(bitmap)
        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        drawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }


    private fun getCameraUpdateForBounds(latLngBounds: LatLngBounds): CameraUpdate {
        val width = resources.displayMetrics.widthPixels
        val height = resources.displayMetrics.heightPixels
        val padding = (width * 0.1).toInt()

        return CameraUpdateFactory.newLatLngBounds(latLngBounds, width, height, padding)
    }


    private fun getMap(listener: OnMapReadyCallback) {
        if (map == null) {
            oneTimeListeners.add(listener)
        } else {
            listener.onMapReady(map)
        }
    }

}