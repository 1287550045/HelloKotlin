package com.chuanxi.hellokotlin.db

import com.chuanxi.hellokotlin.domain.ForecastList
import com.chuanxi.hellokotlin.domain.datasource.ForecastDataSource
import com.chuanxi.hellokotlin.extensions.clear
import com.chuanxi.hellokotlin.extensions.parseList
import com.chuanxi.hellokotlin.extensions.parseOpt
import com.chuanxi.hellokotlin.extensions.toVarargArray
import com.chuanxi.hellokotlin.model.CityForecast
import com.chuanxi.hellokotlin.model.DayForecast
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import java.util.*

/**
 * Created by tangjunjie on 2016/2/20.
 */
class ForecastDb(val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance,val dataMapper: DbDataMapper = DbDataMapper()):ForecastDataSource {

    override fun requestForecastByZipCode(zipCode: Long, date: Long) = forecastDbHelper.use{
        val dailyRequest = "${DayForecastTable.CITY_ID} = ? AND ${DayForecastTable.DATE} >=?"
        val dailyForecast = select(DayForecastTable.NAME)
                .whereSimple(dailyRequest,zipCode.toString(),date.toString())
                .parseList { DayForecast(HashMap(it)) }
        val city = select(CityForecastTable.NAME)
                .whereSimple("${CityForecastTable.ID}",zipCode.toString())
                .parseOpt { CityForecast(HashMap(it),dailyForecast) }
        if(city==null) null else dataMapper.convertToDomain(city)
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