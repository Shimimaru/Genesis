package com.example.Genesis.menu.Map

import android.graphics.Color
import com.beust.klaxon.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread
import org.json.JSONException
import java.net.URL
import java.util.ArrayList

class MapRoute(var map: Map, var googleMap: GoogleMap, mapDB: MapDB)
{
    init{

    }

    fun createRoute(dest1 : LatLng,dest2 : LatLng){
        val LatLongB = LatLngBounds.Builder()
        //googleMap!!.addMarker(MarkerOptions().position(dest1).title("Marker1"))
        googleMap.addMarker(MarkerOptions().position(dest2).title("Marker2"))
        val options = PolylineOptions()
        options.color(Color.RED)
        options.width(5f)
        val url = getURL(dest1, dest2)
        System.out.println(url)
        async {
            val result = URL(url).readText()
            uiThread {
                val parser: Parser = Parser()
                val stringBuilder: StringBuilder = StringBuilder(result)
                val json: JsonObject = parser.parse(stringBuilder) as JsonObject
                try
                {
                    val routes = json.array<JsonObject>("routes")
                    val points = routes!!["legs"]["steps"][0] as JsonArray<JsonObject>
                    val polypts = points.flatMap { decodePoly(it.obj("polyline")?.string("points")!!)  }
                    options.add(dest1)
                    LatLongB.include(dest1)
                    for (point in polypts)  {
                        options.add(point)
                        LatLongB.include(point)
                    }
                    options.add(dest2)
                    LatLongB.include(dest2)
                    val bounds = LatLongB.build()
                    googleMap!!.addPolyline(options)
                    googleMap!!.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 20))
                } catch (e: JSONException) {
                    e.printStackTrace();
                }
            }
        }
    }

    private fun getURL(from : LatLng, to : LatLng) : String {
        val origin = "origin=" + from.latitude + "," + from.longitude
        val dest = "destination=" + to.latitude + "," + to.longitude
        val sensor = "sensor=false"
        val params = "$origin&$dest&$sensor&mode=driving&key=AIzaSyCyby5CgdNevoEkTeUPla9-37ZW4qjB2wg"
        return "https://maps.googleapis.com/maps/api/directions/json?$params"
    }

    private fun decodePoly(encoded: String): List<LatLng> {
        val poly = ArrayList<LatLng>()
        var index = 0
        val len = encoded.length
        var lat = 0
        var lng = 0

        while (index < len) {
            var b: Int
            var shift = 0
            var result = 0
            do {
                b = encoded[index++].toInt() - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlat = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lat += dlat

            shift = 0
            result = 0
            do {
                b = encoded[index++].toInt() - 63
                result = result or (b and 0x1f shl shift)
                shift += 5
            } while (b >= 0x20)
            val dlng = if (result and 1 != 0) (result shr 1).inv() else result shr 1
            lng += dlng

            val p = LatLng(lat.toDouble() / 1E5,
                lng.toDouble() / 1E5)
            poly.add(p)
        }

        return poly
    }
}