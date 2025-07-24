package com.example.testcoffe.locations.data.network

import com.example.testcoffe.locations.data.db.LocationDto
import retrofit2.http.GET
import retrofit2.http.Header

interface LocationApi {
    @GET("locations")
    suspend fun getLocations(
        @Header("Authorization") token: String,
    ): List<LocationDto>
}