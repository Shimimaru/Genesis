package com.example.Genesis.objects

import com.google.android.gms.maps.model.LatLng

class Quest(
    var id : Int,
    var name: String,
    var description: String,
    var level: Int,
    var latlng: LatLng?
) {
    override fun toString(): String {
        return "Hello, world."
    }

}