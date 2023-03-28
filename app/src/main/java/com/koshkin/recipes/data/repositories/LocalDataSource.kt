package com.koshkin.recipes.data.repositories


import com.koshkin.recipes.domain.entity.RecipesForFragment
import com.koshkin.recipes.domain.entity.sent.SentIdDomain
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
                              // ScreenDB
    suspend fun saveAll(recipes: List<RecipesForFragment>)

    suspend fun deleteAll()

    suspend fun getAll():List<RecipesForFragment>

                                // SentDB
    suspend fun insertId(id:SentIdDomain)

    suspend fun getSaveId(): List<SentIdDomain>

}