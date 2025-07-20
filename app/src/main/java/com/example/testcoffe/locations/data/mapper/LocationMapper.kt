package com.example.testcoffe.locations.data.mapper

import com.example.testcoffe.locations.data.db.LocationDto
import com.example.testcoffe.locations.domain.model.Location

fun LocationDto.toDomain(): Location = Location(
    id = id,
    name = name,
    latitude = point.latitude,
    longitude = point.longitude
)