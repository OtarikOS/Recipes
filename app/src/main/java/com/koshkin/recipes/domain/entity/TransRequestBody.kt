package com.koshkin.recipes.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class TransRequestBody(
    var sourceLanguageCode : String           = "en",
    var format             : String           = "PLAIN_TEXT",
    var texts              : ArrayList<String> = arrayListOf(),
    var folderId           : String?          = null,
    var targetLanguageCode : String?          = null,
  // var model              : String?           = null,
  //  var glossaryConfig     : GlossaryConfig?   = GlossaryConfig()
)
