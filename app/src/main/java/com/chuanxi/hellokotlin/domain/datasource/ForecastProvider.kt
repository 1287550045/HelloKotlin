package com.chuanxi.hellokotlin.domain.datasource

import android.util.Log
import com.chuanxi.hellokotlin.data.server.ForecastServer
import com.chuanxi.hellokotlin.db.ForecastDb
import com.chuanxi.hellokotlin.domain.Forecast
import com.chuanxi.hellokotlin.domain.ForecastList
import com.chuanxi.hellokotlin.extensions.fistResult

/**
 * Created by tangjunjie on 2016/2/22.
 */
class ForecastProvider(val sources: List<ForecastDataSource> = ForecastProvider.SOURCES) {

    companion object {
        val DAY_IN_MILLIS = 1000 * 60 * 60 * 24
        val SOURCES = listOf<ForecastDataSource>(ForecastDb(), ForecastServer())
    }

    fun requestByZipCode(zipCode: Long, days: Int): ForecastList = requestToSources {
        val res = it.requestForecastByZipCode(zipCode, todayTimeSpan())
        if (res != null && res.size() >= days) res else null
    }

    fun requestForecast(id:Long): Forecast = requestToSources { it.requestDayForecast(id) }

    fun <T : Any> requestToSources(f : (ForecastDataSource)->T?) :T = sources.fistResult{ f(it)}

    private fun todayTimeSpan() = System.currentTimeMillis() / DAY_IN_MILLIS * DAY_IN_MILLIS
}