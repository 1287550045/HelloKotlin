package com.chuanxi.hellokotlin.data

import android.util.Log
import com.google.gson.Gson
import java.net.URL
/**
 * Created by tangjunjie on 2016/2/19.
 */
class ForecastRequest(val zipCode: Long, val gson: Gson = Gson()) {

    companion object {
        private val APP_ID = "15646a06818f61f7b8d7823ca833e1ce"
        private val URL = "http://api.openweathermap.org/data/2.5/" +
                "forecast/daily?mode=json&units=metric&cnt=7"
        private val COMPLETE_URL = "$URL&APPID=$APP_ID&q="
    }

    public fun execute(): ForecastResult {
        val forecastJsonStr = URL(COMPLETE_URL + zipCode).readText()
        Log.e(javaClass.simpleName,forecastJsonStr)
        return gson.fromJson(forecastJsonStr, ForecastResult::class.java)
    }
}