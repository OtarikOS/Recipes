package com.koshkin.recipes.data.mappers.sent

import com.koshkin.recipes.data.db.sent.SentIdDb
import com.koshkin.recipes.domain.entity.sent.SentIdDomain

class SentIdMapper {
    fun toSentDb(input: SentIdDomain): SentIdDb{return SentIdDb(id = input.id)}

    fun toSentDomain(input: SentIdDb): SentIdDomain{return SentIdDomain(id = input.id)
    }
}