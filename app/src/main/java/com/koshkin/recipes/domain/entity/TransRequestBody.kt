package com.koshkin.recipes.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class TransRequestBody(
   // var sourceLanguageCode : String?           = null,
    var format             : String?           = null,
    var texts              : ArrayList<String> = arrayListOf(),
    var folderId           : String?           = null,
    var targetLanguageCode : String?           = null,
  // var model              : String?           = null,
  //  var glossaryConfig     : GlossaryConfig?   = GlossaryConfig()
)
