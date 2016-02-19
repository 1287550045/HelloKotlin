package com.chuanxi.hellokotlin

import java.util.*

/**
 * 数据类的javabean
 * Created by tangjunjie on 2016/2/19.
 */
data class Forecast(val date:Date,val temperature:Float,val details:String) {
}