package com.koshkin.recipes.data.repositories

import com.koshkin.recipes.data.api.RecipesAPI
import com.koshkin.recipes.data.mappers.RecipesApiResponseMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.koshkin.recipes.domain.common.Result
import com.koshkin.recipes.domain.entity.Results

class RecipesRemoteDataSourceImp(
    private val service: RecipesAPI,
    private val mapper: RecipesApiResponseMapper
): RecipesRemoteDataSource {
    override suspend fun getRecipes(tag: String?, ingredient: String): Result<List<Results>> =
        withContext(Dispatchers.IO){
            try {
                val response = service.getListRecipes(tag, ingredient)
                if(response.isSuccessful) {
                    return@withContext Result.Success(mapper.responseToResults(response.body()!!))
                }
                else{
                    return@withContext Result.Error(Exception(response.message()))
                }
            }catch (e: Exception){
                return@withContext Result.Error(e)
            }
        }

    override suspend fun getRecipe(recipeID: Int): Result<Results> =
        withContext(Dispatchers.IO){
            try {
                val response = service.getRecipeInfo(recipeID)
                if(response.isSuccessful) {
                    return@withContext Result.Success(mapper.toResults(response.body()!!))
                }
                else{
                    return@withContext Result.Error(Exception(response.message()))
                }
            }catch (e: Exception){
                return@withContext Result.Error(e)
            }
        }
}