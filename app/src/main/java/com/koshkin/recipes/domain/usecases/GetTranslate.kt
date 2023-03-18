package com.koshkin.recipes.domain.usecases

import com.koshkin.recipes.domain.repositories.RecipesRepository
import okhttp3.RequestBody

class GetTranslate(private val repository: RecipesRepository) {
    suspend operator fun invoke(authorizationKey: String,requestBody: RequestBody) = repository.translate(authorizationKey, requestBody)
}