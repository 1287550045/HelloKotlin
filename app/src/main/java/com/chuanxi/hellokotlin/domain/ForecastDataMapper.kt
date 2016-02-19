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
        return ForecastList(forecast.city.name, forecast.city.country,
                convertForecastListToDomain(forecast.list))
    }

    private fun convertForecastListToDomain(list: List<Forecast>):
            List<ModelForecast> {
        return list.map { convertForecastItemToDomain(it) }
    }

    private fun convertForecastItemToDomain(forecast: Forecast): ModelForecast {
        return ModelForecast(convertDate(forecast.dt),
                forecast.weather[0].description, forecast.temp.max.toInt(),
                forecast.temp.min.toInt())
    }

    private fun convertDate(date: Long): String {
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return df.format(date * 1000)
    }
}
