package com.chuanxi.hellokotlin.db

import com.chuanxi.hellokotlin.domain.Forecast
import com.chuanxi.hellokotlin.domain.ForecastList
import com.chuanxi.hellokotlin.model.CityForecast
import com.chuanxi.hellokotlin.model.DayForecast

/**
 * Created by tangjunjie on 2016/2/20.
 */
class DbDataMapper {
    fun convertFromDomain(forecastlist:ForecastList) = with(forecastlist) {
        val day = dailyForecast.map { convertDayFromDomain(id,it) }
        CityForecast(id,city,country,day)
    }

    fun convertDayFromDomain(cityId:Long,forecast: Forecast) = with(forecast) {
        DayForecast(date,description,high,low,iconUrl,cityId)
    }
    fun convertToDomain(forecast: CityForecast) = with(forecast) {
        val daily = dailyForecast.map { convertDayToDomain(it) }
        ForecastList(_id, city, country, daily)
    }
    fun convertDayToDomain(dayForecast: DayForecast) = with(dayForecast) {
        Forecast(_id,date,description,high,low,iconUrl)
    }
}