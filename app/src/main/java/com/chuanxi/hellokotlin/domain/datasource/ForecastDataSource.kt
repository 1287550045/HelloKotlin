package com.chuanxi.hellokotlin.domain.datasource

import com.chuanxi.hellokotlin.domain.Forecast
import com.chuanxi.hellokotlin.domain.ForecastList

/**
 * Created by tangjunjie on 2016/2/20.
 */
interface ForecastDataSource {

    fun requestForecastByZipCode(zipCode:Long,date:Long) : ForecastList?
    fun requestDayForecast(id: Long): Forecast?
}