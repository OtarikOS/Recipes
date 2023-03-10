package com.koshkin.recipes.data.repositories

import com.koshkin.recipes.domain.common.Result
import com.koshkin.recipes.domain.entity.Recipes
import com.koshkin.recipes.domain.entity.Results
import com.koshkin.recipes.domain.repositories.RecipesRepository

class RecipesRepositoryImpl(private val remoteDataSource: RecipesRemoteDataSource):RecipesRepository {
    override suspend fun getRemoteRecipes(tag: String?, ingredient: String?): Result<List<Results>> {
        return remoteDataSource.getRecipes(tag, ingredient)
    }

    override suspend fun getRemoteRecipeInfo(recipeID: Int): Result<Results> {
        return remoteDataSource.getRecipe(recipeID)
    }

}