package com.example.testcoffe.menu.data.mapper

import com.example.testcoffe.menu.data.dto.MenuDto
import com.example.testcoffe.menu.domain.model.Menu

fun MenuDto.toDomain(): Menu = Menu(
    id = id,
    name = name,
    imageURL = imageURL,
    price = price
)