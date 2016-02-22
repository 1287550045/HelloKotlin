package com.chuanxi.hellokotlin.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.chuanxi.hellokotlin.ui.App
import org.jetbrains.anko.db.ManagedSQLiteOpenHelper

/**
 * Created by tangjunjie on 2016/2/20.
 */
class ForecastDbHelper(val ctx : Context = App.instance) : ManagedSQLiteOpenHelper(ctx,
        ForecastDbHelper.DB_NAME, null, ForecastDbHelper.DB_VERSION) {
    companion object {
        val DB_NAME = "forecast.db"
        val DB_VERSION = 1
        val instance: ForecastDbHelper by lazy { ForecastDbHelper() }
    }

    override fun onCreate(db: SQLiteDatabase) {
        createTable(db)
    }

    fun createTable(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE IF NOT EXISTS ${CityForecastTable.NAME} (${CityForecastTable.ID} INTEGER PRIMARY KEY," +
                "${CityForecastTable.CITY} TEXT,${CityForecastTable.COUNTRY} TEXT)")
        db.execSQL("CREATE TABLE IF NOT EXISTS ${DayForecastTable.NAME} (${DayForecastTable.ID} INTEGER PRIMARY KEY," +
                "${DayForecastTable.DATE} INTEGER,${DayForecastTable.DESCRIPTION} TEXT,${DayForecastTable.HIGH} INTEGER,"+
                "${DayForecastTable.LOW} INTEGER,${DayForecastTable.ICON_URL} TEXT,${DayForecastTable.CITY_ID} INTEGER)")
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE ${CityForecastTable.NAME}")
        db.execSQL("DROP TABLE ${DayForecastTable.NAME}")
        createTable(db)
    }
}