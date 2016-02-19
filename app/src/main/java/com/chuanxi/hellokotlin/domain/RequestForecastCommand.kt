package com.chuanxi.hellokotlin.domain

import com.chuanxi.hellokotlin.data.ForecastRequest

/**
 * Created by tangjunjie on 2016/2/19.
 */
class RequestForecastCommand(val zipCode: String) :
        Command<ForecastList> {
    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(
                forecastRequest.execute())
    }
}