package com.koshkin.recipes

import android.app.Application
import com.koshkin.recipes.data.repositories.RecipesRemoteDataSourceImp
import com.koshkin.recipes.data.repositories.RecipesRepositoryImpl
import com.koshkin.recipes.di.ServiceLocator
import com.koshkin.recipes.domain.usecases.*

class App: Application() {
    private val recipesRepository : RecipesRepositoryImpl
        get() = ServiceLocator.provideRecipesRepository(this)

    val getRecipeInfo: GetRecipeInfo
    get() = GetRecipeInfo(recipesRepository)

    val getRemoteRecipes: GetRemoteRecipes
    get() = GetRemoteRecipes(recipesRepository)

    val postRecipe:PostRecipe
    get() = PostRecipe(recipesRepository)

    val getKey:GetKey
    get() = GetKey(recipesRepository)

    val getTranslate: GetTranslate
        get() = GetTranslate(recipesRepository)
}