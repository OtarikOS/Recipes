package com.koshkin.recipes

import android.app.Application
import com.koshkin.recipes.data.repositories.RecipesRepositoryImpl
import com.koshkin.recipes.di.ServiceLocator
import com.koshkin.recipes.domain.usecases.*
import com.koshkin.recipes.presentation.sent.RecipeWithStatusMapper

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

    val deleteAll:DeleteAll
    get() = DeleteAll(recipesRepository)

    val getSavedRecipes: GetSavedRecipes
    get() = GetSavedRecipes(recipesRepository)

    val saveAllRecipes: SaveAllRecipes
    get() = SaveAllRecipes(recipesRepository)

    val saveSent: SaveSent
    get() = SaveSent(recipesRepository)

    val getSent: GetSent
    get() = GetSent(recipesRepository)

    val recipeWithStatusMapper: RecipeWithStatusMapper
    get() = RecipeWithStatusMapper()
}