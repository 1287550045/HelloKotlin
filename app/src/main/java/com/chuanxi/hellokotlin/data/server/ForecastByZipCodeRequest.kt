package com.chuanxi.hellokotlin.data.server

import android.util.Log
import com.chuanxi.hellokotlin.data.ForecastResult
import com.google.gson.Gson

/**
 * Created by tangjunjie on 2016/2/22.
 */
class ForecastByZipCodeRequest(val zipCode: Long, val gson: Gson = Gson()) {

    companion object {
        private val APP_ID = "15646a06818f61f7b8d7823ca833e1ce"
        private val URL = "http://api.openweathermap.org/data/2.5/" +
                "forecast/daily?mode=json&units=metric&cnt=7"
        private val COMPLETE_URL = "$URL&APPID=$APP_ID&q="
    }

    public fun execute(): ForecastResult {
        val forecastJsonStr = java.net.URL(COMPLETE_URL + zipCode).readText()
        Log.e(javaClass.simpleName, forecastJsonStr)
        return gson.fromJson(forecastJsonStr, ForecastResult::class.java)
    }
}