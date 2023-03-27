package com.koshkin.recipes.presentation.sent

import com.koshkin.recipes.domain.entity.RecipesForFragment
import com.koshkin.recipes.domain.entity.sent.SentIdDomain

class RecipeWithStatusMapper {
    fun  fromRecipeToRecipeWithStatus(
        recipes: List<RecipesForFragment>,
        sentRecipes: List<SentIdDomain>
    ): List<RecipesForFragmentWithStatus>{
        val element = recipes.filter { it.id in sentRecipes.map { sent -> sent.id } }
        val recipesWithStatus = arrayListOf<RecipesForFragmentWithStatus>()
        for (recipe in recipes){
            if (recipe in element){
                recipesWithStatus.add(
                    RecipesForFragmentWithStatus(
                        recipe.id,
                        recipe.name,
                        recipe.imageUrl,
                        SentRecipeStatus.SENT
                    )
                )
            }else{
                recipesWithStatus.add(
                    RecipesForFragmentWithStatus(
                        recipe.id,
                        recipe.name,
                        recipe.imageUrl,
                        SentRecipeStatus.NOTSENT
                    )
                )
            }
        }
  //      return recipesWithStatus.sortedBy { it.id }
        return recipesWithStatus
    }

    fun fromRecipeWithStatusToRecipeForFragment(withStatus: RecipesForFragmentWithStatus): RecipesForFragment{
        return RecipesForFragment(
            withStatus.id, withStatus.name, withStatus.imageUrl
        )
    }
}