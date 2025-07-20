package com.example.testcoffe.locations.domain.model

import com.example.testcoffe.locations.data.db.PointDto

data class Location(
    val id: Long,
    val name: String,
    val latitude: Double,
    val longitude: Double
)
