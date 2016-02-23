package com.chuanxi.hellokotlin.db

import android.util.Log
import com.chuanxi.hellokotlin.domain.Forecast
import com.chuanxi.hellokotlin.domain.ForecastList
import com.chuanxi.hellokotlin.domain.datasource.ForecastDataSource
import com.chuanxi.hellokotlin.extensions.*
import com.chuanxi.hellokotlin.model.CityForecast
import com.chuanxi.hellokotlin.model.DayForecast
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import java.util.*

/**
 * Created by tangjunjie on 2016/2/20.
 */
class ForecastDb(val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance,
                 val dataMapper: DbDataMapper = DbDataMapper()):ForecastDataSource {

    override fun requestForecastByZipCode(zipCode: Long, date: Long) = forecastDbHelper.use{
        val dailyRequest = "${DayForecastTable.CITY_ID} = ? AND ${DayForecastTable.DATE} >=?"
        val dailyForecast = select(DayForecastTable.NAME)
                .whereSimple(dailyRequest,zipCode.toString(),(date/1000).toString())
                .parseList { DayForecast(HashMap(it)) }
        var city = select(CityForecastTable.NAME)
                    .whereSimple("${CityForecastTable.ID} = ?",zipCode.toString())
                    .parseOpt { CityForecast(HashMap(it),dailyForecast) }
        city?.let { dataMapper.convertToDomain(it) }
    }

    override fun requestDayForecast(id: Long): Forecast? = forecastDbHelper.use{
        val forecast = select(DayForecastTable.NAME).byId(id).parseOpt {
            DayForecast(HashMap(it))
        }
        forecast?.let { dataMapper.convertDayToDomain(it) }
    }


    fun saveForecastList(forecastList: ForecastList) = forecastDbHelper.use {
        clear(DayForecastTable.NAME)
        clear(CityForecastTable.NAME)
        with(dataMapper.convertFromDomain(forecastList)) {
            insert(CityForecastTable.NAME, *map.toVarargArray())
            dailyForecast.forEach {
                insert(DayForecastTable.NAME,*it.map.toVarargArray())
            }
        }
    }
}