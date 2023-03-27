package com.koshkin.recipes.domain.repositories

import  com.koshkin.recipes.domain.common.Result
import com.koshkin.recipes.domain.entity.*
import com.koshkin.recipes.domain.entity.sent.SentIdDomain
import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody

interface RecipesRepository {
    suspend fun getRemoteRecipes(from: Int,size: Int,tag: String?,ingredient: String?): Result<List<RecipesForFragment>>

    suspend fun getRemoteRecipeInfo(recipeID:Int): Result<Results>

    suspend fun postRecipe(requestBody: RequestBody): Int

    suspend fun getKey():Result<KeyT>

    suspend fun translate(authorizationKey: String,requestBody: RequestBody):Translate

    suspend fun saveAll(recipes: List<RecipesForFragment>)

    suspend fun deleteAll()

    suspend fun getAll():List<RecipesForFragment>

                                          // SentId
    suspend fun insertId(id: SentIdDomain)

    suspend fun getSaveId(): Flow<List<SentIdDomain>>
}