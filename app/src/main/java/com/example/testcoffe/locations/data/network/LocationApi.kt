package com.example.testcoffe.locations.data.network

import com.example.testcoffe.locations.data.db.LocationDto
import retrofit2.http.GET

interface LocationApi {
    @GET("locations")
    suspend fun getLocations(): List<LocationDto>
}