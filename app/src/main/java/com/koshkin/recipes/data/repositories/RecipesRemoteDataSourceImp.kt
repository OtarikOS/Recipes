package com.koshkin.recipes.data.repositories

import android.util.Log
import android.widget.Toast
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.koshkin.recipes.data.api.RecipesAPI
import com.koshkin.recipes.data.mappers.RecipesApiResponseMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.koshkin.recipes.domain.common.Result
import com.koshkin.recipes.domain.entity.Results
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.RequestBody
import retrofit2.Response

class RecipesRemoteDataSourceImp(
    private val service: RecipesAPI,
    private val mapper: RecipesApiResponseMapper
): RecipesRemoteDataSource {
    override suspend fun getRecipes(
        from: Int,
        size: Int,
        tag: String?,
        ingredient: String?
    ): Result<List<Results>> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.getListRecipes(from, size, tag, ingredient)
                if (response.isSuccessful) {
                    return@withContext Result.Success(mapper.responseToResults(response.body()!!))
                } else {
                    return@withContext Result.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext Result.Error(e)
            }
        }

    override suspend fun getRecipe(recipeID: Int): Result<Results> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.getRecipeInfo(recipeID)
                if (response.isSuccessful) {
                    return@withContext Result.Success(mapper.toResults(response.body()!!))
                } else {
                    return@withContext Result.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext Result.Error(e)
            }
        }

    override suspend fun postRecipe(requestBody: RequestBody): Int =
        withContext(Dispatchers.IO) {
       //     try {
                val response = service.postRecipe(requestBody)
                if (response.isSuccessful) {
                    Log.i("res_RRDS",response.body().toString())
      //              return@withContext Result.Success(1)
//                        Toast.makeText(
//                        this@RecipesRemoteDataSourceImp,
//                        "Усё норм",
//                        Toast.LENGTH_SHORT
//                    ))
                    // Convert raw JSON to pretty JSON using GSON library
                    val gson = GsonBuilder().setPrettyPrinting().create()
                    val prettyJson = gson.toJson(
                        JsonParser.parseString(
                            response.body()?.string() // About this thread blocking annotation : https://github.com/square/retrofit/issues/3255
                        )
                    )
//
                    Log.d("Pretty Printed JSON :", prettyJson)

                } else {
                    Log.i("res_RRDS_else",response.errorBody().toString())
                   // return@withContext Result.Error(Exception(response.message()))

                }
//            } catch (e: Exception) {
//                Log.i("res_RRDS_catch",e.message.toString())
////             //   return@withContext Result.Error(e)
          // }
        }
}