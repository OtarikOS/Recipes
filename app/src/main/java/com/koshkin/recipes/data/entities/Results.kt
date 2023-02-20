package com.koshkin.recipes.data.entities

import com.google.gson.annotations.SerializedName

data class Results (
    @SerializedName("renditions"               ) var renditions            : ArrayList<Renditions>   = arrayListOf(),
    @SerializedName("canonical_id"             ) var canonicalId           : String?                 = null,
    @SerializedName("seo_path"                 ) var seoPath               : String?                 = null,
    @SerializedName("brand"                    ) var brand                 : String?                 = null,
    @SerializedName("compilations"             ) var compilations          : ArrayList<String>       = arrayListOf(),
    @SerializedName("aspect_ratio"             ) var aspectRatio           : String?                 = null,
    @SerializedName("thumbnail_alt_text"       ) var thumbnailAltText      : String?                 = null,
    @SerializedName("servings_noun_plural"     ) var servingsNounPlural    : String?                 = null,
    @SerializedName("nutrition"                ) var nutrition             : Nutrition?              = Nutrition(),
    @SerializedName("approved_at"              ) var approvedAt            : Int?                    = null,
    @SerializedName("topics"                   ) var topics                : ArrayList<Topics>       = arrayListOf(),
    @SerializedName("total_time_tier"          ) var totalTimeTier         : TotalTimeTier?          = TotalTimeTier(),
    @SerializedName("yields"                   ) var yields                : String?                 = null,
    @SerializedName("original_video_url"       ) var originalVideoUrl      : String?                 = null,
    @SerializedName("cook_time_minutes"        ) var cookTimeMinutes       : Int?                    = null,
    @SerializedName("nutrition_visibility"     ) var nutritionVisibility   : String?                 = null,
    @SerializedName("id"                       ) var id                    : Int?                    = null,
    @SerializedName("slug"                     ) var slug                  : String?                 = null,
    @SerializedName("sections"                 ) var sections              : ArrayList<Sections>     = arrayListOf(),
    @SerializedName("tips_and_ratings_enabled" ) var tipsAndRatingsEnabled : Boolean?                = null,
    @SerializedName("beauty_url"               ) var beautyUrl             : String?                 = null,
    @SerializedName("updated_at"               ) var updatedAt             : Int?                    = null,
    @SerializedName("country"                  ) var country               : String?                 = null,
    @SerializedName("instructions"             ) var instructions          : ArrayList<Instructions> = arrayListOf(),
    @SerializedName("keywords"                 ) var keywords              : String?                 = null,
    @SerializedName("facebook_posts"           ) var facebookPosts         : ArrayList<String>       = arrayListOf(),
    @SerializedName("brand_id"                 ) var brandId               : String?                 = null,
    @SerializedName("created_at"               ) var createdAt             : Int?                    = null,
    @SerializedName("price"                    ) var price                 : Price?                  = Price(),
    @SerializedName("show_id"                  ) var showId                : Int?                    = null,
    @SerializedName("show"                     ) var show                  : Show?                   = Show(),
    @SerializedName("description"              ) var description           : String?                 = null,
    @SerializedName("draft_status"             ) var draftStatus           : String?                 = null,
    @SerializedName("video_id"                 ) var videoId               : Int?                    = null,
    @SerializedName("video_url"                ) var videoUrl              : String?                 = null,
    @SerializedName("is_one_top"               ) var isOneTop              : Boolean?                = null,
    @SerializedName("servings_noun_singular"   ) var servingsNounSingular  : String?                 = null,
    @SerializedName("prep_time_minutes"        ) var prepTimeMinutes       : Int?                    = null,
    @SerializedName("num_servings"             ) var numServings           : Int?                    = null,
    @SerializedName("buzz_id"                  ) var buzzId                : String?                 = null,
    @SerializedName("inspired_by_url"          ) var inspiredByUrl         : String?                 = null,
    @SerializedName("total_time_minutes"       ) var totalTimeMinutes      : Int?                    = null,
    @SerializedName("video_ad_content"         ) var videoAdContent        : String?                 = null,
    @SerializedName("seo_title"                ) var seoTitle              : String?                 = null,
    @SerializedName("is_shoppable"             ) var isShoppable           : Boolean?                = null,
    @SerializedName("promotion"                ) var promotion             : String?                 = null,
    @SerializedName("language"                 ) var language              : String?                 = null,
    @SerializedName("user_ratings"             ) var userRatings           : UserRatings?            = UserRatings(),
    @SerializedName("tags"                     ) var tags                  : ArrayList<Tags>         = arrayListOf(),
    @SerializedName("name"                     ) var name                  : String?                 = null,
    @SerializedName("thumbnail_url"            ) var thumbnailUrl          : String?                 = null,
    @SerializedName("credits"                  ) var credits               : ArrayList<Credits>      = arrayListOf()

)

data class Credits (

    @SerializedName("name" ) var name : String? = null,
    @SerializedName("type" ) var type : String? = null

)

data class Tags (

    @SerializedName("name"         ) var name        : String? = null,
    @SerializedName("id"           ) var id          : Int?    = null,
    @SerializedName("display_name" ) var displayName : String? = null,
    @SerializedName("type"         ) var type        : String? = null

)

data class UserRatings (

    @SerializedName("count_positive" ) var countPositive : Int? = null,
    @SerializedName("score"          ) var score         : Int? = null,
    @SerializedName("count_negative" ) var countNegative : Int? = null

)

data class Show (

    @SerializedName("name" ) var name : String? = null,
    @SerializedName("id"   ) var id   : Int?    = null

)

data class Price (

    @SerializedName("updated_at"          ) var updatedAt          : String? = null,
    @SerializedName("portion"             ) var portion            : Int?    = null,
    @SerializedName("consumption_total"   ) var consumptionTotal   : Int?    = null,
    @SerializedName("consumption_portion" ) var consumptionPortion : Int?    = null,
    @SerializedName("total"               ) var total              : Int?    = null

)

data class Instructions (

    @SerializedName("start_time"   ) var startTime   : Int?    = null,
    @SerializedName("appliance"    ) var appliance   : String? = null,
    @SerializedName("end_time"     ) var endTime     : Int?    = null,
    @SerializedName("temperature"  ) var temperature : String? = null,
    @SerializedName("id"           ) var id          : Int?    = null,
    @SerializedName("position"     ) var position    : Int?    = null,
    @SerializedName("display_text" ) var displayText : String? = null

)

data class Sections (

    @SerializedName("position"   ) var position   : Int?                  = null,
    @SerializedName("components" ) var components : ArrayList<Components> = arrayListOf(),
    @SerializedName("name"       ) var name       : String?               = null

)

data class Components (

    @SerializedName("position"      ) var position     : Int?                    = null,
    @SerializedName("measurements"  ) var measurements : ArrayList<Measurements> = arrayListOf(),
    @SerializedName("raw_text"      ) var rawText      : String?                 = null,
    @SerializedName("extra_comment" ) var extraComment : String?                 = null,
    @SerializedName("ingredient"    ) var ingredient   : Ingredient?             = Ingredient(),
    @SerializedName("id"            ) var id           : Int?                    = null

)

data class Unit (

    @SerializedName("display_plural"   ) var displayPlural   : String? = null,
    @SerializedName("display_singular" ) var displaySingular : String? = null,
    @SerializedName("abbreviation"     ) var abbreviation    : String? = null,
    @SerializedName("system"           ) var system          : String? = null,
    @SerializedName("name"             ) var name            : String? = null

)

data class Ingredient (

    @SerializedName("updated_at"       ) var updatedAt       : Int?    = null,
    @SerializedName("name"             ) var name            : String? = null,
    @SerializedName("created_at"       ) var createdAt       : Int?    = null,
    @SerializedName("display_plural"   ) var displayPlural   : String? = null,
    @SerializedName("id"               ) var id              : Int?    = null,
    @SerializedName("display_singular" ) var displaySingular : String? = null

)

data class Measurements (

    @SerializedName("unit"     ) var unit     : Unit?   = Unit(),
    @SerializedName("quantity" ) var quantity : String? = null,
    @SerializedName("id"       ) var id       : Int?    = null

)

data class TotalTimeTier (

    @SerializedName("tier"         ) var tier        : String? = null,
    @SerializedName("display_tier" ) var displayTier : String? = null

)

data class Topics (

    @SerializedName("name" ) var name : String? = null,
    @SerializedName("slug" ) var slug : String? = null

)

data class Nutrition (

    @SerializedName("sugar"         ) var sugar         : Int?    = null,
    @SerializedName("carbohydrates" ) var carbohydrates : Int?    = null,
    @SerializedName("fiber"         ) var fiber         : Int?    = null,
    @SerializedName("updated_at"    ) var updatedAt     : String? = null,
    @SerializedName("protein"       ) var protein       : Int?    = null,
    @SerializedName("fat"           ) var fat           : Int?    = null,
    @SerializedName("calories"      ) var calories      : Int?    = null

)

data class Renditions(
    @SerializedName("file_size"        ) var fileSize       : Int?    = null,
    @SerializedName("duration"         ) var duration       : Int?    = null,
    @SerializedName("bit_rate"         ) var bitRate        : Int?    = null,
    @SerializedName("aspect"           ) var aspect         : String? = null,
    @SerializedName("minimum_bit_rate" ) var minimumBitRate : String? = null,
    @SerializedName("name"             ) var name           : String? = null,
    @SerializedName("maximum_bit_rate" ) var maximumBitRate : String? = null,
    @SerializedName("container"        ) var container      : String? = null,
    @SerializedName("poster_url"       ) var posterUrl      : String? = null,
    @SerializedName("url"              ) var url            : String? = null,
    @SerializedName("content_type"     ) var contentType    : String? = null,
    @SerializedName("width"            ) var width          : Int?    = null,
    @SerializedName("height"           ) var height         : Int?    = null
)


