package com.tuzgen.myapplication

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MainActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        val fragment: Fragment = MapFragment()
        supportFragmentManager.beginTransaction().replace(R.id.map, fragment).commit()

        // Request location permission
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        } else {
            // Permission already granted
// Get current location and pass it to com.tuzgen.myapplication.MapFragment
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location : Location ->
                    // Pass location data to com.tuzgen.myapplication.MapFragment
                    val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as MapFragment?
                    mapFragment?.updateMapLocation(location.latitude, location.longitude)
                }
                .addOnFailureListener { exception ->
                }

//            getCurrentLocation()
        }
    }
//
//    private fun getCurrentLocation() {
////        fusedLocationClient.lastLocation
////            .addOnSuccessListener { location : Location ->
////                // Pass location data to com.tuzgen.myapplication.MapFragment
//        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as com.tuzgen.myapplication.MapFragment?
//        mapFragment?.updateMapLocation(location.latitude, location.longitude)
//    }
////            .addOnFailureListener { exception ->
////                // Handle failure
////            }
////    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, get location
                fusedLocationClient.lastLocation
                    .addOnSuccessListener { location : Location ->
                        // Pass location data to com.tuzgen.myapplication.MapFragment
                        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as MapFragment?
                        println("Location: ${location.latitude} ${location.longitude}")
                        mapFragment?.updateMapLocation(location.latitude, location.longitude)
                    }
                    .addOnFailureListener { exception ->
                        // Handle failure
                    }

//                val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as com.tuzgen.myapplication.MapFragment?
//                mapFragment?.updateMapLocation(location.latitude, location.longitude)
            } else {
                // Permission denied, handle accordingly
            }
        }
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 100
    }
}
