package com.koshkin.recipes.data.mappers

import com.koshkin.recipes.data.api.InstructionsApi
import com.koshkin.recipes.data.api.RecipesApiResponse
import com.koshkin.recipes.data.api.ResultsApi
import com.koshkin.recipes.domain.entity.Instructions
import com.koshkin.recipes.domain.entity.Results
import com.koshkin.recipes.domain.entity.Tags

class RecipesApiResponseMapper {
    fun responseToResults(response: RecipesApiResponse): List<Results> {
        return response.results.map {
            Results(
                it.id,
                it.instructions.map {
                    Instructions(
                        it.temperature,
                        it.id,
                        it.displayText
                    )
                },
                it.description,
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

    fun toResults (resultsApi: ResultsApi): Results{
        return Results(
            id =resultsApi.id,
            instructions =  resultsApi.instructions.map {
                Instructions(
                    it.temperature,
                    it.id,
                    it.temperature
                )
            }
        )
    }
}