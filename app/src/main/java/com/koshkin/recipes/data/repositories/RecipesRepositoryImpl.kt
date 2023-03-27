package com.koshkin.recipes.data.repositories

import com.koshkin.recipes.domain.common.Result
import com.koshkin.recipes.domain.entity.*
import com.koshkin.recipes.domain.entity.sent.SentIdDomain
import com.koshkin.recipes.domain.repositories.RecipesRepository
import kotlinx.coroutines.flow.Flow
import okhttp3.RequestBody

class RecipesRepositoryImpl(
    private val remoteDataSource: RecipesRemoteDataSource,
    private val localDataSource: LocalDataSource
) : RecipesRepository {
    override suspend fun getRemoteRecipes(
        from: Int,
        size: Int,
        tag: String?,
        ingredient: String?
    ): Result<List<RecipesForFragment>> {
        return remoteDataSource.getRecipes(from, size, tag, ingredient)
    }


    override suspend fun getRemoteRecipeInfo(recipeID: Int): Result<Results> {
        return remoteDataSource.getRecipe(recipeID)
    }

    override suspend fun postRecipe(requestBody: RequestBody): Int {
        return remoteDataSource.postRecipe(requestBody)
    }

    override suspend fun getKey(): Result<KeyT> {
        return remoteDataSource.getKey()
    }

    override suspend fun translate(authorizationKey: String, requestBody: RequestBody): Translate {
        return remoteDataSource.translate(authorizationKey, requestBody)
    }

    override suspend fun saveAll(recipes: List<RecipesForFragment>) {
        return localDataSource.saveAll(recipes)
    }

    override suspend fun deleteAll() {
        return localDataSource.deleteAll()
    }

    override suspend fun getAll(): List<RecipesForFragment> {
        return localDataSource.getAll()
    }

                               //SendID

    override suspend fun getSaveId(): Flow<List<SentIdDomain>> {
        return localDataSource.getSaveId()
    }

    override suspend fun insertId(id: SentIdDomain) {
        return localDataSource.insertId(id)
    }

}