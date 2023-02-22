package com.koshkin.recipes.domain.entity

import com.google.gson.annotations.SerializedName

data class Results (
//     var renditions            : ArrayList<Renditions>   = arrayListOf(),
//     var canonicalId           : String?                 = null,
//     var seoPath               : String?                 = null,
//     var brand                 : String?                 = null,
//     var compilations          : ArrayList<String>       = arrayListOf(),
//     var aspectRatio           : String?                 = null,
//     var thumbnailAltText      : String?                 = null,
//     var servingsNounPlural    : String?                 = null,
//     var nutrition             : Nutrition?              = Nutrition(),
//     var approvedAt            : Int?                    = null,
//     var topics                : ArrayList<Topics>       = arrayListOf(),
//     var totalTimeTier         : TotalTimeTier?          = TotalTimeTier(),
//     var yields                : String?                 = null,
//     var originalVideoUrl      : String?                 = null,
//     var cookTimeMinutes       : Int?                    = null,
//     var nutritionVisibility   : String?                 = null,
     var id                    : Int?                    = null,
     var slug                  : String?                 = null,
     var sections              : ArrayList<Sections>     = arrayListOf(),
     var tipsAndRatingsEnabled : Boolean?                = null,
     var beautyUrl             : String?                 = null,
     var updatedAt             : Int?                    = null,
     var country               : String?                 = null,
     var instructions          : ArrayList<Instructions> = arrayListOf(),
     var keywords              : String?                 = null,
     var facebookPosts         : ArrayList<String>       = arrayListOf(),
     var brandId               : String?                 = null,
     var createdAt             : Int?                    = null,
     var price                 : Price?                  = Price(),
     var showId                : Int?                    = null,
     var show                  : Show?                   = Show(),
     var description           : String?                 = null,
//     var draftStatus           : String?                 = null,
//     var videoId               : Int?                    = null,
//     var videoUrl              : String?                 = null,
//     var isOneTop              : Boolean?                = null,
//     var servingsNounSingular  : String?                 = null,
//     var prepTimeMinutes       : Int?                    = null,
//     var numServings           : Int?                    = null,
//     var buzzId                : String?                 = null,
//     var inspiredByUrl         : String?                 = null,
//     var totalTimeMinutes      : Int?                    = null,
//     var videoAdContent        : String?                 = null,
//     var seoTitle              : String?                 = null,
//     var isShoppable           : Boolean?                = null,
//     var promotion             : String?                 = null,
//     var language              : String?                 = null,
//     var userRatings           : UserRatings?            = UserRatings(),
     var tags                  : List<Tags>         = arrayListOf(),
     var name                  : String?                 = null,
     var thumbnailUrl          : String?                 = null,
 //    var credits               : ArrayList<Credits>      = arrayListOf()

)

data class Credits (

     var name : String? = null,
     var type : String? = null

)

data class Tags (

     var name        : String? = null,
     var id          : Int?    = null,
     var displayName : String? = null,
     var type        : String? = null

)

data class UserRatings (

     var countPositive : Int? = null,
     var score         : Int? = null,
     var countNegative : Int? = null

)

data class Show (

     var name : String? = null,
     var id   : Int?    = null

)

data class Price (

     var updatedAt          : String? = null,
     var portion            : Int?    = null,
     var consumptionTotal   : Int?    = null,
     var consumptionPortion : Int?    = null,
     var total              : Int?    = null

)

data class Instructions (

//     var startTime   : Int?    = null,
//     var appliance   : String? = null,
//     var endTime     : Int?    = null,
     var temperature : String? = null,
     var id          : Int?    = null,
  //   var position    : Int?    = null,
     var displayText : String? = null

)

data class Sections (

     var position   : Int?                  = null,
     var components : ArrayList<Components> = arrayListOf(),
     var name       : String?               = null

)

data class Components (

     var position     : Int?                    = null,
     var measurements : ArrayList<Measurements> = arrayListOf(),
     var rawText      : String?                 = null,
     var extraComment : String?                 = null,
     var ingredient   : Ingredient?             = Ingredient(),
     var id           : Int?                    = null

)

data class Unit (

     var displayPlural   : String? = null,
     var displaySingular : String? = null,
     var abbreviation    : String? = null,
     var system          : String? = null,
     var name            : String? = null

)

data class Ingredient (

     var updatedAt       : Int?    = null,
     var name            : String? = null,
     var createdAt       : Int?    = null,
     var displayPlural   : String? = null,
     var id              : Int?    = null,
     var displaySingular : String? = null

)

data class Measurements (

     var unit     : Unit?   = Unit(),
     var quantity : String? = null,
     var id       : Int?    = null

)

data class TotalTimeTier (

     var tier        : String? = null,
     var displayTier : String? = null

)

data class Topics (

     var name : String? = null,
     var slug : String? = null

)

data class Nutrition (

     var sugar         : Int?    = null,
     var carbohydrates : Int?    = null,
     var fiber         : Int?    = null,
     var updatedAt     : String? = null,
     var protein       : Int?    = null,
     var fat           : Int?    = null,
     var calories      : Int?    = null

)

data class Renditions (
     var fileSize       : Int?    = null,
     var duration       : Int?    = null,
     var bitRate        : Int?    = null,
     var aspect         : String? = null,
     var minimumBitRate : String? = null,
     var name           : String? = null,
     var maximumBitRate : String? = null,
     var container      : String? = null,
     var posterUrl      : String? = null,
     var url            : String? = null,
     var contentType    : String? = null,
     var width          : Int?    = null,
     var height         : Int?    = null
)