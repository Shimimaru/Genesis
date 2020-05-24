package com.example.Genesis.menu.Map

import com.google.android.gms.maps.model.LatLng

class MapContract {

    interface MapInterface{

    }

    interface MapLocationInterface{

    }

    interface MapMarkerInterface{

    }

    interface MapRouteInterface{
        fun createRoute(dest1 : LatLng, dest2 : LatLng)
        private fun getURL(from : LatLng, to : LatLng) : String {return ""}
        private fun decodePoly(encoded: String): List<LatLng> {return emptyList()}
    }

    interface MapDBInterface{

    }
}