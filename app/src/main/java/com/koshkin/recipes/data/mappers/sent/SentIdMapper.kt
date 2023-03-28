package com.koshkin.recipes.data.mappers.sent

import com.koshkin.recipes.data.db.sent.SentIdDb
import com.koshkin.recipes.domain.entity.Results
import com.koshkin.recipes.domain.entity.sent.SentIdDomain

class SentIdMapper {
    fun toSentDbList(input: List<SentIdDomain>): List<SentIdDb>{
        return input.map {
            SentIdDb(
                it.id!!
            )
        }
    }

    fun toSentDomain(input: List<SentIdDb>): List<SentIdDomain>{
        return input.map {
            SentIdDomain(
                it.id
            )
        }
    }

    fun  toSentDb(input:SentIdDomain):SentIdDb{return SentIdDb(id = input.id!!)
    }

}