package com.koshkin.recipes.data.repositories

import com.koshkin.recipes.domain.common.Result
import com.koshkin.recipes.domain.entity.KeyTrans
import com.koshkin.recipes.domain.entity.Recipes
import com.koshkin.recipes.domain.entity.Results
import com.koshkin.recipes.domain.repositories.RecipesRepository
import okhttp3.RequestBody

class RecipesRepositoryImpl(private val remoteDataSource: RecipesRemoteDataSource):RecipesRepository {
    override suspend fun getRemoteRecipes(from: Int,size: Int,tag: String?, ingredient: String?): Result<List<Results>> {
        return remoteDataSource.getRecipes(from, size, tag, ingredient)
    }


    override suspend fun getRemoteRecipeInfo(recipeID: Int): Result<Results> {
        return remoteDataSource.getRecipe(recipeID)
    }

    override suspend fun postRecipe(requestBody: RequestBody): Int {
        return remoteDataSource.postRecipe(requestBody)
    }

    override suspend fun getKey(): KeyTrans {
        return  remoteDataSource.getKey()
    }

}