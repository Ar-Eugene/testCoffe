package com.example.testcoffe.locations.data.impl

import com.example.testcoffe.locations.data.mapper.toDomain
import com.example.testcoffe.locations.data.network.LocationApi
import com.example.testcoffe.locations.domain.model.Location
import com.example.testcoffe.locations.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(private val locationApi: LocationApi): LocationRepository {
    override fun getLocation(token: String): Flow<List<Location>> = flow {
        val locations = locationApi.getLocations("Bearer $token").map { it.toDomain() }
        emit(locations)
    }
}