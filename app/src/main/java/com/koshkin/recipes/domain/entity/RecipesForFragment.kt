package com.koshkin.recipes.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

data class RecipesForFragment(
    val id: Int?,
    val name: String?,
    val imageUrl:String?
)
