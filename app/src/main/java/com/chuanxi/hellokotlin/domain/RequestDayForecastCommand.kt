package com.chuanxi.hellokotlin.domain

import com.chuanxi.hellokotlin.domain.datasource.ForecastProvider

/**
 * Created by tangjunjie on 2016/2/22.
 */
class RequestDayForecastCommand (val id:Long,val forecastProvider: ForecastProvider= ForecastProvider()) : Command<Forecast>{
    override fun execute() = forecastProvider.requestForecast(id)
}