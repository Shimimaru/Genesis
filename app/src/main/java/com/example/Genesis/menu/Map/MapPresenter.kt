package com.example.Genesis.menu.Map

import com.example.Genesis.menu.Quest.QuestDatabase
import com.example.Genesis.menu.Quest.QuestMenu.QuestMenu
import com.example.Genesis.objects.Quest
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
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
        questList = questDB.getQuest()
        for(i in 0 until questList.size){
            print(i)
            mapMarker.addQuestMarker(googleMap,questList.get(i))
        }
        googleMap.addMarker(
            MarkerOptions()
                .position(mapLocation.getCurrentLocation())
                .title("Current Location")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
        )
        var trackedLocation = questDB.getTrackedQuest().latlng
        if (trackedLocation != null) {
            mapRoute.createRoute(mapLocation.getCurrentLocation(),trackedLocation)
        }
    }
}