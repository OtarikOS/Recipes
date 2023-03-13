package com.koshkin.recipes.data.mappers

import com.koshkin.recipes.data.api.InstructionsApi
import com.koshkin.recipes.data.api.RecipesApiResponse
import com.koshkin.recipes.data.api.ResultsApi
import com.koshkin.recipes.domain.entity.*

class RecipesApiResponseMapper {
    fun responseToResults(response: RecipesApiResponse): List<Results> {
        return response.results.map {
            Results(
                it.aspectRatio,
                Nutrition(it.nutrition?.sugar,
                it.nutrition?.carbohydrates,
                it.nutrition?.fiber,
                it.nutrition?.updatedAt,
                it.nutrition?.protein,
                it.nutrition?.fat,
                it.nutrition?.calories),
                it.topics.map {
                    Topics(
                        it.name,
                        it.slug,
                    )
                },
                it.yields,
                it.cookTimeMinutes,
                it.id,
                it.sections.map {
                                Sections(
                                    it.position,
                                    it.components.map {
                                                      Components(
                                                          it.position,
                                                          it.measurements.map {
                                                                              Measurements(
                                                                                  it.quantity,
                                                                                  it.id,
                                                                              )
                                                          },
                                                          it.rawText,
                                                          it.extraComment,
                                                          Ingredient(it.ingredient?.updatedAt,
                                                                  it.ingredient?.name,
                                                                  it.ingredient?.createdAt,
                                                                  it.ingredient?.displayPlural,
                                                                  it.ingredient?.id,
                                                                  it.ingredient?.displaySingular),



                                                          it.id,
                                                      )
                                    },
                                    it.name,
                                )
                },
                it.country,
                it.instructions.map {
                    Instructions(
                        it.startTime,
                        it.appliance,
                        it.displayText,
                        it.endTime,
                        it.temperature,
                        it.id,
                        it.position,


                        )
                },
                it.description,
                it.prepTimeMinutes,
                it.totalTimeMinutes,
               UserRatings(
                   it.userRatings?.countPositive,
                   it.userRatings?.countNegative
               ),
                it.tags.map {
                    Tags(
                        it.name,
                        it.id,
                        it.displayName,
                        it.type
                    )
                },
                it.name,
                it.thumbnailUrl
            )
        }
    }


    fun toResults(resultsApi: ResultsApi): Results {
        return Results(
            aspectRatio = resultsApi.aspectRatio,
            nutrition = Nutrition(resultsApi.nutrition?.sugar,
                resultsApi.nutrition?.carbohydrates,
                resultsApi.nutrition?.fiber,
                resultsApi.nutrition?.updatedAt,
                resultsApi.nutrition?.protein,
                resultsApi.nutrition?.fat,
                resultsApi.nutrition?.calories),
            topics=resultsApi.topics.map {
                                 Topics(
                                     it.name,
                                     it.slug
                                 )
            },
            yields=resultsApi.yields,
            cookTimeMinutes = resultsApi.cookTimeMinutes,
            id = resultsApi.id,
            sections = resultsApi.sections.map {
              Sections(
                  it.position,
                  it.components.map {
                                    Components(
                                        it.position,
                                        it.measurements.map {
                                                            Measurements(
                                                                it.quantity,
                                                                it.id,
                                                            )
                                        },
                                        it.rawText,
                                        it.extraComment,
                                        Ingredient(
                                            it.ingredient?.updatedAt,
                                            it.ingredient?.name,
                                            it.ingredient?.createdAt,
                                            it.ingredient?.displayPlural,
                                            it.ingredient?.id,
                                            it.ingredient?.displaySingular,
                                    ),
                                        it.id,
                                    )
                  },
                  it.name,
              )
            },
            country= resultsApi.country,
            instructions = resultsApi.instructions.map {
                Instructions(
                    it.startTime,
                    it.appliance,
                    it.displayText,
                    it.endTime,
                    it.temperature,
                    it.id,
                    it.position,
                )
            },
            description = resultsApi.description,
            prepTimeMinutes = resultsApi.prepTimeMinutes,
            totalTimeMinutes = resultsApi.totalTimeMinutes,
            userRatings = UserRatings(
                resultsApi.userRatings?.countPositive,
                resultsApi.userRatings?.countNegative
            ),
            tags = resultsApi.tags.map {
                Tags(
                    it.name,
                    it.id,
                    it.displayName,
                    it.type
                )
            },
            name = resultsApi.name,
            thumbnailUrl = resultsApi.thumbnailUrl
        )
    }
}