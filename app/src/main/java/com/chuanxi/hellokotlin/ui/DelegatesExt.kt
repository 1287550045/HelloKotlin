package com.chuanxi.hellokotlin.ui

import kotlin.properties.ReadWriteProperty

/**
 * Created by tangjunjie on 2016/2/19.
 */
object DelegatesExt {
     fun <T> notNullSingleValue():
            ReadWriteProperty<Any?, T> = NotNullSingleValueVar()
}