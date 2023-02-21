package com.koshkin.recipes.data.api

import com.google.gson.annotations.SerializedName
import com.koshkin.recipes.data.entities.Results

data class RecipesApiResponse (
    @SerializedName("count"   ) var count   : Int?               = null,
    @SerializedName("results" ) var results : ArrayList<Results> = arrayListOf()

)