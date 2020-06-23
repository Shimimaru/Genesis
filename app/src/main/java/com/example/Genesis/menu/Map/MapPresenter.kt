package com.example.Genesis.menu.Map

import com.example.Genesis.menu.Account.planner.EventDesc
import com.example.Genesis.menu.Quest.QuestDatabase
import com.example.Genesis.menu.Quest.QuestMenu.QuestMenu
import com.example.Genesis.objects.Quest
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapPresenter(var map : Map,var googleMap: GoogleMap) {

    var mapDB : MapDB
    var mapMarker : MapMarker
    var mapLocation : MapLocation
    var mapRoute : MapRoute
    var questDB : QuestDatabase = QuestDatabase()
    var questList : ArrayList<Quest> = ArrayList<Quest>()

    init{
        mapDB = MapDB()
        mapMarker = MapMarker(map,googleMap,mapDB)
        mapLocation = MapLocation(map,googleMap,mapDB)
        mapRoute = MapRoute(map,googleMap,mapDB)
        val cameraPosition = CameraPosition.Builder()
            .target(mapLocation.getCurrentLocation())
            .zoom(15f)
            .build()
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        questList = questDB.getQuest()
        if(questList.size != 0) {
            for (i in 0 until questList.size) {
                print(i)
                mapMarker.addQuestMarker(googleMap, questList.get(i))
            }
            if(questDB.getTrackedQuest() != null) {
                googleMap.addMarker(
                    MarkerOptions()
                        .position(mapLocation.getCurrentLocation())
                        .title("Current Location")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                )//Be sure your location is set within New Zealand because it cant create a route overseas.
                var trackedLocation = questDB.getTrackedQuest().latlng
                if (QuestMenu.trackedQuest != -1) {
                    var currentLocation = mapLocation.getCurrentLocation()
                    if (trackedLocation != null && currentLocation != null) {
                        mapRoute.createRoute(currentLocation, trackedLocation)
                    }
            }
            }
        }
    }
}