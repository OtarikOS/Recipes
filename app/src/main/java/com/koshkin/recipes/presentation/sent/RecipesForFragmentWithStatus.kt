package com.koshkin.recipes.presentation.sent

data class RecipesForFragmentWithStatus(
    val id: Int?,
    val name: String?,
    val imageUrl:String?,
    val status: SentRecipeStatus
)
