package com.koshkin.recipes.domain.transformation

import com.koshkin.recipes.domain.entity.Results
import java.util.regex.Pattern

class ConvertedResults(private val results: Results) {

    private var bodyResult: ArrayList<String> = arrayListOf()


    private fun convertString(basic: String?, replacement: String?): String {
        var split = Pattern.compile(" ").split(basic, 3)
        return "${replacement}g ${split[2]}"

    }
    private fun searchGram(array: Array<String>){

    }

    fun toArrayForRequest(): ArrayList<String> {
        for (position: Int in 0 until results.topics.size) {
            bodyResult.add(results.topics[position].name ?: "7777")
            if (position == results.topics.size - 1) {
                bodyResult.add("8888")
            }
        }
        bodyResult.add(results.yields ?: "7777")

        for (position_sect: Int in 0..results.sections.size) {
            if (position_sect == results.sections.size) {
                bodyResult.add("8888")
                break
            }else {
                bodyResult.add(results.sections[position_sect].name ?: "7777")
            for (position_comp: Int in 0..results.sections[position_sect].components.size) {

                if (position_comp == results.sections[position_sect].components.size) {
                    bodyResult.add("8888")
                    break
                } else if (results.sections[position_sect].components[position_comp].measurements.size>1) { //Проверка для замены фунтов на граммы
                    val array = arrayListOf<Int>()
                 //   var replacement: String
                  //  for(position_meas: Int in 0 until results.sections[position_sect].components[position_comp].measurements.size) {
                        for (i: Int in 0 until results.sections[position_sect].components[position_comp].measurements.size) {
                            results.sections[position_sect].components[position_comp].measurements[i].quantity!!.toIntOrNull()
                                ?.let { array.add(it) }
                        }
                   // }
                   val replacement = array.max().toString()
                    val str =
                        convertString(
                            results.sections[position_sect].components[position_comp].rawText,
                            replacement
                        )
                    bodyResult.add(str)

                    bodyResult.add(
                        results.sections[position_sect].components[position_comp].rawText ?: "7777"
                    )
                    bodyResult.add(
                        results.sections[position_sect].components[position_comp].ingredient?.name
                            ?: "7777"
                    )
                }
            }
            }
        }
        bodyResult.add(results.country ?: "7777")

        for (position_inst: Int in 0..results.instructions.size) {
            if (position_inst == results.instructions.size) {
                bodyResult.add("8888")
            }else{
                bodyResult.add(results.instructions[position_inst].displayText!!)
            }
        }

        bodyResult.add(results.description ?: "7777")

        for (position_tags: Int in 0 .. results.tags.size) {
            if (position_tags == results.tags.size) {
                bodyResult.add("8888")
            }else {
                bodyResult.add(results.tags[position_tags].displayName ?: "7777")
            }
        }

        bodyResult.add(results.name!!)

        return bodyResult

    }

    fun fromBodyResponse(response: ArrayList<String>, results: Results) {

        var position: Int = 0

        for (position_t: Int in 0..results.topics.size) {
            if (!response[position].equals("7777") && !response[position].equals("8888")) {
                results.topics[position_t].name = response[position]
                position++
            } else if (response[position].equals("8888")) {
                position++
            } else {
                results.topics[position_t].name = ""
                position++
            }
        }

        if (!response[position].equals("7777")) {
            results.yields = response[position]
            position++
        } else {
            results.yields = ""
            position++
        }

        for (position_sect: Int in 0..results.sections.size) {
            if (response[position].equals("7777")) {
                results.sections[position_sect].name = null
                position++
            } else if (response[position].equals("8888")) {
                position++
                break
            } else {
                results.sections[position_sect].name = response[position]
                position++
            }

            for (position_comp: Int in 0..results.sections[position_sect].components.size) {
                if (response[position].equals("7777")) {
                    results.sections[position_sect].components[position_comp].rawText = ""
                    position++
                } else if (response[position].equals("8888")) {
                    position++
                    break
                } else {
                    results.sections[position_sect].components[position_comp].rawText =
                        response[position]
                    position++
                    results.sections[position_sect].components[position_comp].ingredient?.name =
                        response[position]
                    position++
                }
            }
        }

        if (response[position].equals("7777")) {
            results.country = ""
            position++
        } else {
            results.country = response[position]
            position++
        }

        for (position_inst: Int in 0..results.instructions.size) {
            if (response[position].equals("7777")) {
                results.instructions[position_inst].displayText = ""
                position++
            } else if (response[position].equals("8888")) {
                position++
                break
            } else {
                results.instructions[position_inst].displayText = response[position]
                position++
            }
        }

        if (response[position].equals("7777")) {
            results.description = ""
            position++
        } else {
            results.description = response[position]
            position++
        }

        for (position_tag: Int in 0..results.tags.size) {
            if (response[position].equals("7777")) {
                results.tags[position_tag].displayName = ""
                position++
            } else if (response[position].equals("8888")) {
                position++
                break
            } else {
                results.tags[position_tag].displayName = response[position]
                position++
            }
        }

        if (response[position].equals("7777")) {
            results.name = ""
        } else {
            results.name = response[position]
        }


    }

}

