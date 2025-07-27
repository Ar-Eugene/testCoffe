package com.example.testcoffe.locations.data.impl

import com.example.testcoffe.menu.data.mapper.toDomain
import com.example.testcoffe.menu.data.network.MenuApi
import com.example.testcoffe.menu.domain.model.Menu
import com.example.testcoffe.menu.domain.repository.MenuRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MenuRepositoryImpl @Inject constructor(private val menuApi: MenuApi) :
    MenuRepository {
    override fun getMenu(locationId: Long, token: String): Flow<List<Menu>> = flow {
        val menus = menuApi.getMenu(locationId, "Bearer $token").map { it.toDomain() }
        emit(menus)
    }
}