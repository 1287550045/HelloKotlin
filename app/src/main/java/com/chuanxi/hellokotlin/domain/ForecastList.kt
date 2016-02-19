package com.chuanxi.hellokotlin.domain

/**
 * Created by tangjunjie on 2016/2/19.
 */
data class ForecastList(val city: String, val country: String,
                        val dailyForecast:List<Forecast>)

data class Forecast(val date: String, val description: String, val high: Int,
                    val low: Int)