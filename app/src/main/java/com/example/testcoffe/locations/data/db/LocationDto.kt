package com.example.testcoffe.locations.data.db

data class LocationDto(
    val id: Long,
    val name: String,
    val point: PointDto
)

data class PointDto(
    val latitude: Double,
    val longitude: Double
)
