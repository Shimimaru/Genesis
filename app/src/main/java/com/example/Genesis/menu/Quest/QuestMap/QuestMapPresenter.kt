package com.example.Genesis.menu.Quest.QuestMap

import com.example.Genesis.menu.Map.MapDB
import com.example.Genesis.menu.Map.MapLocation
import com.example.Genesis.menu.Map.MapRoute
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*


class QuestMapPresenter(var map: QuestMap, var googleMap: GoogleMap) {
    var mapDB : MapDB
    lateinit var mapLocation : QuestLocation
    lateinit var mapRoute : MapRoute

    init{
        mapDB = MapDB()
        mapLocation = QuestLocation(map,googleMap,mapDB);
        val cameraPosition = CameraPosition.Builder()
            .target(mapLocation.getCurrentLocation())
            .zoom(15f)
            .build()
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        setMapLongClick(googleMap)
    }


    fun setMapLongClick(map: GoogleMap) {
        map.setOnMapLongClickListener { latLng ->
            var snippet = String.format(
                Locale.getDefault(),
                "Lat: %1$.5f, Long: %2$.5f",
                latLng.latitude,
                latLng.longitude
            )
            latLngValue = LatLng(latLng.latitude,latLng.longitude)
            map.addMarker(
                MarkerOptions()
                    .position(latLng)
                    .snippet(snippet)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
            )
        }
    }

    companion object{
        var latLngValue : LatLng? = null
    }
}