package com.example.Genesis.menu.Quest.QuestMap

import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.Genesis.menu.Map.Map
import com.example.Genesis.menu.Map.MapDB
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng

class QuestLocation(var map : QuestMap, var googleMap: GoogleMap, var mapDB: MapDB){

    private var REQUEST_LOCATION_PERMISSION = 1
    var fusedLocationClient: FusedLocationProviderClient = map.fusedLocationClient
    private var currentLocation : LatLng = LatLng(-36.966539,174.923868)

    init{

    }

    fun getCurrentLocation(): LatLng {
        enableMyLocation()
        return currentLocation
    }

    private fun isPermissionGranted() : Boolean {
        return ContextCompat.checkSelfPermission(
            map,
            Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    private fun enableMyLocation() {
        if (isPermissionGranted()) {
            googleMap.isMyLocationEnabled = true
            getLastKnownLocation()
        }
        else {
            ActivityCompat.requestPermissions(
                map,
                arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        }
    }

    private fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray) {
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.contains(PackageManager.PERMISSION_GRANTED)) {
                enableMyLocation()
            }
        }
    }

    private fun getLastKnownLocation(){
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location->
                if (location != null) {
                    currentLocation = LatLng(location.latitude,location.longitude)
                    System.out.println(location.latitude + location.longitude)
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 17f))
                }
            }
    }


}