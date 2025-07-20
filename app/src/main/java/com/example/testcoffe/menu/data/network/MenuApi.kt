package com.example.testcoffe.menu.data.network

import com.example.testcoffe.menu.data.dto.MenuDto
import retrofit2.http.GET
import retrofit2.http.Path

interface MenuApi {
    @GET("location/{id}/menu")
    suspend fun getMenu(@Path("id") id: Long): List<MenuDto>
}