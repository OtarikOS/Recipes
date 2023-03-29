package com.koshkin.recipes.presentation.sent

import com.koshkin.recipes.data.db.sent.SentIdDb
import com.koshkin.recipes.domain.entity.RecipesForFragment
import com.koshkin.recipes.domain.entity.Results
import com.koshkin.recipes.domain.entity.sent.SentIdDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RecipeWithStatusMapper {
    fun  fromRecipeToRecipeWithStatus(
        recipes: List<RecipesForFragment>,
        sentRecipes: List<SentIdDomain>
    ): List<RecipesForFragmentWithStatus>{
     //   val element = recipes.filter { it.id in sentRecipes.map { sent -> sent.id } }
        val recipesWithStatus = arrayListOf<RecipesForFragmentWithStatus>()



        for (recipe in recipes) {
            if (sentRecipes.isEmpty()) {
                recipesWithStatus.add(
                    RecipesForFragmentWithStatus(
                        recipe.id,
                        recipe.name,
                        recipe.imageUrl,
                        status = SentRecipeStatus.NOTSENT
                    )

                )
            } else {

                for (i:Int in 0 until sentRecipes.size) {
                    if (recipe.id == sentRecipes[i].id) {
                        recipesWithStatus.add(
                            RecipesForFragmentWithStatus(
                                recipe.id,
                                recipe.name,
                                recipe.imageUrl,
                                SentRecipeStatus.SENT
                            )
                        )
                        break
                    } else if(i ==sentRecipes.size-1){
                        recipesWithStatus.add(
                            RecipesForFragmentWithStatus(
                                recipe.id,
                                recipe.name,
                                recipe.imageUrl,
                                SentRecipeStatus.NOTSENT
                            )
                        )
                       // continue
                    }
                }
            }
        }
      //  return recipesWithStatus.sortedBy { it.id }
        return recipesWithStatus
    }

    fun fromRecipeWithStatusToRecipeForFragment(withStatus: RecipesForFragmentWithStatus): RecipesForFragment{
        return RecipesForFragment(
            withStatus.id, withStatus.name, withStatus.imageUrl
        )
    }

    fun fromWithStatusToSentIdDomain(recipe: RecipesForFragmentWithStatus): SentIdDomain{
        return SentIdDomain(id = recipe.id)
    }
    fun resultsToSendIdDomain(recipe: Results): SentIdDomain{return SentIdDomain(id = recipe.id)
    }
}