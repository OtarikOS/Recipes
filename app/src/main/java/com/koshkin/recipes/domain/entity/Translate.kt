package com.koshkin.recipes.domain.entity

import com.google.gson.annotations.SerializedName

data class Translate(
    @SerializedName("translations" ) var translations : ArrayList<Translations> = arrayListOf()
)

data class Translations (

    @SerializedName("text"                 ) var text                 : String? = null,
    @SerializedName("detectedLanguageCode" ) var detectedLanguageCode : String? = null

)
