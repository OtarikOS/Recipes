package com.koshkin.recipes.data.mappers

import com.koshkin.recipes.data.api.KeyTrans
import com.koshkin.recipes.domain.entity.KeyT


class KeyMapper {
    fun toKeyTransApi(keyT: KeyT): KeyTrans {return KeyTrans(key = keyT.key)}

    fun toKeyT(keyTrans: KeyTrans):KeyT{return KeyT(key = keyTrans.key)
    }
}