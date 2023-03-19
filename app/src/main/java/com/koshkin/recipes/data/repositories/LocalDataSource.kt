package com.koshkin.recipes.data.repositories


import com.koshkin.recipes.domain.entity.RecipesForFragment

interface LocalDataSource {

    suspend fun saveAll(recipes: List<RecipesForFragment>)

    suspend fun deleteAll()

    suspend fun getAll():List<RecipesForFragment>
}