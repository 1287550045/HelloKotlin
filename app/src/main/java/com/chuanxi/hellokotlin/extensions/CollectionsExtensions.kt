package com.chuanxi.hellokotlin.extensions

/**
 * Created by tangjunjie on 2016/2/22.
 */
fun <K, V : Any> MutableMap<K, V?>.toVarargArray(): Array<out Pair<K, V>>
        = map { Pair(it.key, it.value!!) }.toTypedArray()


inline fun <T,R :Any> Iterable<T>.fistResult(predicate: (T) -> R?) : R {
        for(element in this) {
                val result = predicate(element)
                if(result!=null) return result
        }
        throw NoSuchMethodException("No element matching predicate was found.")
}