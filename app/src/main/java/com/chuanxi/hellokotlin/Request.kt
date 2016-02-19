package com.chuanxi.hellokotlin

import android.util.Log
import java.net.URL

/**
 *
 * 执行网络请求的工具类
 * Created by tangjunjie on 2016/2/19.
 */
public class Request(val url:String) {

    public fun run() {
        val forecastJsonStr = URL(url).readText()
        Log.e(javaClass.simpleName,forecastJsonStr)
    }
}