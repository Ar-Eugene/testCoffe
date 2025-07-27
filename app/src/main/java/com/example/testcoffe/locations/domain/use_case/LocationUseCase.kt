package com.example.testcoffe.locations.domain.use_case

import com.example.testcoffe.locations.domain.model.Location
import com.example.testcoffe.locations.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocationUseCase @Inject constructor(private val locationRepository: LocationRepository) {
    operator fun invoke(token: String): Flow<List<Location>> = locationRepository.getLocation(token)
}