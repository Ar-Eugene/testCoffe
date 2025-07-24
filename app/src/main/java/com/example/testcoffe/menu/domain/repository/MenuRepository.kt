package com.example.testcoffe.menu.domain.repository

import com.example.testcoffe.menu.domain.model.Menu
import kotlinx.coroutines.flow.Flow

interface MenuRepository {
    fun getMenu(locationId: Long, token: String): Flow<List<Menu>>
}