package com.example.testcoffe.locations.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.util.Log
import com.google.android.gms.location.LocationServices
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class LocationTracker @Inject constructor(
    private val context: Context
) {
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    suspend fun getCurrentLocation(): Location? = suspendCoroutine { cont ->
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                if (location == null) {
                    Log.e("LocationTracker", "Location is null. Can't get user's location.")
                }
                cont.resume(location)
            }
            .addOnFailureListener {
                Log.e("LocationTracker", "Failed to get location: ${it.message}", it)
                cont.resume(null)
            }
    }
}