package com.chuanxi.hellokotlin.domain

import com.chuanxi.hellokotlin.data.ForecastRequest
import com.chuanxi.hellokotlin.domain.datasource.ForecastProvider

/**
 * Created by tangjunjie on 2016/2/19.
 */
class RequestForecastCommand(private val zipCode: Long,
                             val forecastProvider: ForecastProvider = ForecastProvider()) :
        Command<ForecastList> {

    companion object {
        val DAYS = 7
    }
    override fun execute(): ForecastList {
        return forecastProvider.requestByZipCode(zipCode,DAYS)
    }
}