package com.koshkin.recipes.domain.entity

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


//@Serializable
data class KeyTrans(
 @SerializedName("key")   var key: String? = null
)
