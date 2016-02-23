package com.chuanxi.hellokotlin.extensions

import android.content.Context
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by tangjunjie on 2016/2/23.
 */
object DelegatesExt {
    fun longPreference(context: Context,name: String,default: Long) = Preference(context,name,default)

}

class Preference<T>(val context: Context,val name: String,val default:T)
            :ReadWriteProperty<Any?,T> {
    val prefs by lazy {
        context.getSharedPreferences("default",Context.MODE_PRIVATE)
    }
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return findPreference(name,default)
    }

    private fun findPreference(name: String,default: T) = with(prefs) {
        val res:Any = when(default) {
            is Long -> getLong(name,default)
            is Int -> getInt(name,default)
            is String -> getString(name,default)
            is Boolean -> getBoolean(name,default)
            is Float -> getFloat(name,default)
            else -> throw IllegalArgumentException(
                    "This type can be saved into Preferences")
        }
        res as T
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putPreference(name,value)
    }

    private fun <U> putPreference(name: String,value: U) = with(prefs.edit()) {
        when(value) {
            is Long -> putLong(name,value)
            is Int -> putInt(name,value)
            is String -> putString(name,value)
            is Boolean -> putBoolean(name,value)
            is Float -> putFloat(name,value)
            else -> throw IllegalArgumentException(
                    "This type can be saved into Preferences")
        }.apply()
    }
}


class LongPreference(val context: Context,val name:String,val default:Long)
            :ReadWriteProperty<Any?,Long> {
    val prefs by lazy {
        context.getSharedPreferences("default",Context.MODE_PRIVATE)
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): Long {

        return prefs.getLong(name,default)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Long) {
        prefs.edit().putLong(name,value).apply()
    }

}