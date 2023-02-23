package com.koshkin.recipes.di

import android.content.Context
import com.koshkin.recipes.BuildConfig
import com.koshkin.recipes.data.api.NetworkModule
import com.koshkin.recipes.data.mappers.RecipesApiResponseMapper
import com.koshkin.recipes.data.repositories.RecipesRemoteDataSourceImp
import com.koshkin.recipes.data.repositories.RecipesRepositoryImpl

object ServiceLocator {
    private val networkModule by lazy {
        NetworkModule()
    }
    @Volatile
    var recipesRepository: RecipesRepositoryImpl? = null

    fun provideRecipesRepository(context: Context) : RecipesRepositoryImpl{
        synchronized(this){
            return recipesRepository ?: createRecipesRepository(context)
        }
    }

    private fun createRecipesRepository(context: Context): RecipesRepositoryImpl {
        val newRepo =
            RecipesRepositoryImpl(
                RecipesRemoteDataSourceImp(
                networkModule.createRecipesApi(BuildConfig.TASTY_APIS_ENDPOINT),
                RecipesApiResponseMapper()
                )
            )
        recipesRepository = newRepo
        return  newRepo
    }
}