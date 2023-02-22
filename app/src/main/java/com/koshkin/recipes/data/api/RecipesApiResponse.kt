package com.koshkin.recipes.data.api

import com.google.gson.annotations.SerializedName

data class RecipesApiResponse (
    @SerializedName("count"   ) var count   : Int?               = null,
    @SerializedName("results" ) var results : List<ResultsApi> = arrayListOf()

)