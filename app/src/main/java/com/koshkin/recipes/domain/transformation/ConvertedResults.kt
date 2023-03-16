package com.koshkin.recipes.domain.transformation

import com.koshkin.recipes.domain.entity.Results
import java.util.regex.Pattern

class ConvertedResults(private val results: Results) {

    private var bodyResult:ArrayList<String> = arrayListOf()


    private fun convertString(basic: String?, replacement: String?):String{
        var split = Pattern.compile(" ").split(basic,3)
            return  "${replacement}g ${split[3]}"

    }

    fun toArrayForRequest():ArrayList<String>{
        for (position:Int in 0 until results.topics.size){
            bodyResult.add(results.topics[position].name?:"7777")
            if (position == results.topics.size-1){
                bodyResult.add("8888")
            }
        }
        bodyResult.add(results.yields?:"7777")

        for (position_sect:Int in 0 until results.sections.size){
            bodyResult.add(results.sections[position_sect].name?:"7777")
            if (position_sect == results.sections.size){
                bodyResult.add("8888")
            }
            for (position_comp:Int in 0 until results.sections[position_sect].components.size){

                if (!results.sections[position_sect].components[position_comp].measurements[1].quantity!!.isEmpty()) { //Проверка для замены фунтов на граммы
                    val str=
                        convertString(
                            results.sections[position_sect].components[position_comp].rawText,
                            results.sections[position_sect].components[position_comp].measurements[1].quantity
                        )
                    bodyResult.add(str)
                }
                bodyResult.add(results.sections[position_sect].components[position_comp].rawText?:"7777")
                bodyResult.add(results.sections[position_sect].components[position_comp].ingredient?.name?:"7777")

                if (position_comp == results.sections[position_comp].components.size){
                    bodyResult.add("8888")
                }
            }
        }
        bodyResult.add(results.country?:"7777")

        for (position_inst:Int in 0 until results.instructions.size){
            bodyResult.add(results.instructions[position_inst].displayText!!)
            if (position_inst ==results.instructions.size-1){
                bodyResult.add("8888")
            }
        }

        bodyResult.add(results.description?:"7777")

        for (position_tags:Int in 0 until results.tags.size){
            bodyResult.add(results.tags[position_tags].displayName?:"7777")
            if (position_tags == results.tags.size-1){
                bodyResult.add("8888")
            }
        }

        bodyResult.add(results.name!!)

        return bodyResult

    }

    fun fromBodyResponse(response: ArrayList<String>){

        for (position:Int in 0 until response.size){

        }
    }

}

