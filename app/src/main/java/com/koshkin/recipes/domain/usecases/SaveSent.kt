package com.koshkin.recipes.domain.usecases

import com.koshkin.recipes.domain.entity.sent.SentIdDomain
import com.koshkin.recipes.domain.repositories.RecipesRepository

class SaveSent(private val repository: RecipesRepository) {
    suspend operator fun invoke(id:SentIdDomain) = repository.insertId(id)
}