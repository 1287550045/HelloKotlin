package com.chuanxi.hellokotlin.domain

import com.chuanxi.hellokotlin.data.ForecastResult
import java.text.DateFormat
import java.util.*
import com.chuanxi.hellokotlin.domain.Forecast as ModelForecast
import com.chuanxi.hellokotlin.data.Forecast

/**
 * Created by tangjunjie on 2016/2/19.
 */
class ForecastDataMapper {

    public fun convertFromDataModel(forecast: ForecastResult): ForecastList {
        return ForecastList(forecast.city.id,forecast.city.name, forecast.city.country,
                convertForecastListToDomain(forecast.city.id,forecast.list))
    }

    private fun convertForecastListToDomain(id:Long,list: List<Forecast>):
            List<ModelForecast> {
        return list.map { convertForecastItemToDomain(id,it) }
    }

    private fun convertForecastItemToDomain(id:Long,forecast: Forecast): ModelForecast {
        return ModelForecast(id,forecast.dt,
                forecast.weather[0].description, forecast.temp.max.toInt(),
                forecast.temp.min.toInt(),generateIconUrl(forecast.weather[0].icon))
    }

    private fun generateIconUrl(iconCode: String): String
            = "http://openweathermap.org/img/w/$iconCode.png"


}
