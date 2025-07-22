package com.example.testcoffe.locations.domain.repository

import com.example.testcoffe.locations.domain.model.Location
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    fun getLocation(token: String): Flow<List<Location>>
}