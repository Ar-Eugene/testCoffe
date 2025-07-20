package com.example.testcoffe.menu.domain.use_case

import com.example.testcoffe.menu.domain.model.Menu
import com.example.testcoffe.menu.domain.repository.MenuRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MenuUseCase @Inject constructor(private val menuRepository: MenuRepository) {

    operator fun invoke(id: Long): Flow<List<Menu>> = menuRepository.getMenu(id)
}