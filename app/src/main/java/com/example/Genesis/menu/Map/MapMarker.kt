package com.example.Genesis.menu.Map

import com.example.Genesis.objects.Quest
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*

class MapMarker(map : Map,googleMap : GoogleMap, mapDB : MapDB) {
    lateinit var marker : Marker
    lateinit var mapLocaton : MapLocation

    init{
        mapLocaton = MapLocation(map,googleMap,mapDB)
        setMapLongClick(googleMap)
        setPoiClick(googleMap)
    }

    private fun setMapLongClick(map: GoogleMap){
        map.setOnMapLongClickListener { latLng ->
            var snippet = String.format(
                Locale.getDefault(),
                "Lat: %1$.5f, Long: %2$.5f",
                latLng.latitude,
                latLng.longitude
            )
            map.addMarker(
                MarkerOptions()
                    .position(latLng)
                    .snippet(snippet)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
            )
        }
    }

    private fun setPoiClick(map: GoogleMap) {
        map.setOnPoiClickListener { poi ->
            val poiMarker = map.addMarker(
                MarkerOptions()
                    .position(poi.latLng)
                    .title(poi.name)
            )
            poiMarker.showInfoWindow()
        }
    }

    fun addQuestMarker(map: GoogleMap,quest : Quest){
        map.addMarker(
            quest.latlng?.let {
                MarkerOptions()
                    .position(it)
                    .title(quest.name)
            }
        )
    }
}