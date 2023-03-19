package com.koshkin.recipes.data.mappers

import com.koshkin.recipes.data.db.recipesfragmentdb.entity.ScreenDb
import com.koshkin.recipes.domain.entity.RecipesForFragment

class ScreenDbMapper {
    fun toScreenDb(input: List<RecipesForFragment>): List<ScreenDb>{
        return input.map {
            ScreenDb(
                it.id,
                it.name,
                it.imageUrl
            )
        }
    }

    fun toForFragment(input: List<ScreenDb>): List<RecipesForFragment>{
        return input.map {
            RecipesForFragment(
                it.id,
                it.name,
                it.imageUrl
            )
        }

    }
}