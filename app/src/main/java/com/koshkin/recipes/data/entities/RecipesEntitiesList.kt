package com.koshkin.recipes.data.entities

import com.google.gson.annotations.SerializedName

data class RecipesEntitiesList (
    @SerializedName("count"   ) var count   : Int?               = null,
    @SerializedName("results" ) var results : ArrayList<Results> = arrayListOf()

)