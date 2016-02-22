package com.chuanxi.hellokotlin.extensions

import android.database.sqlite.SQLiteDatabase
import com.chuanxi.hellokotlin.domain.Forecast
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.SelectQueryBuilder
import java.util.*

/**
 * Created by tangjunjie on 2016/2/20.
 */

public fun <T : Any> SelectQueryBuilder.parseList(
        parser: (Map<String, Any>) -> T): List<T> =
        parseList(object : MapRowParser<T> {
            override fun parseRow(columns: Map<String, Any>): T = parser(columns)
        })

public fun <T : Any> SelectQueryBuilder.parseOpt(
        parser: (Map<String, Any>) -> T): T? =
        parseOpt(object : MapRowParser<T> {
            override fun parseRow(columns: Map<String, Any>): T = parser(columns)
        })

public fun SQLiteDatabase.clear(table_name:String) {
    this.execSQL("delete from ${table_name}")
}
