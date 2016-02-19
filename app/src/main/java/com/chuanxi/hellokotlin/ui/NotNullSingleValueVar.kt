package com.chuanxi.hellokotlin.ui

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by tangjunjie on 2016/2/19.
 */
class NotNullSingleValueVar<T>() : ReadWriteProperty<Any?,T>{
    private var value:T?= null
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value ?:throw UnsupportedOperationException("")
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = if(this.value==null) value
        else
        throw UnsupportedOperationException()
    }
}