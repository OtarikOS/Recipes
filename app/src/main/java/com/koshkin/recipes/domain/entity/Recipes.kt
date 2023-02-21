package com.koshkin.recipes.domain.entity



data class Recipes(
    var count   : Int?               = null,
    var results : ArrayList<Results> = arrayListOf()
)
