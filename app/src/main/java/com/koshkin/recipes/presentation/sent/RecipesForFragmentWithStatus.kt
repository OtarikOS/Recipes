package com.koshkin.recipes.presentation.sent

import com.koshkin.recipes.presentation.sent.SentRecipeStatus

data class RecipesForFragmentWithStatus(
    val id: Int?,
    val name: String?,
    val imageUrl:String?,
    var status: SentRecipeStatus
)
