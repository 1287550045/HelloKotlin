package com.chuanxi.hellokotlin.extensions

/**
 * Created by tangjunjie on 2016/2/22.
 */
fun <K, V : Any> MutableMap<K, V?>.toVarargArray(): Array<out Pair<K, V>>
        = map { Pair(it.key, it.value!!) }.toTypedArray()