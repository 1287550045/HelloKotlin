package com.chuanxi.hellokotlin.ui

import android.app.Application

/**
 * Created by tangjunjie on 2016/2/19.
 */
class App : Application(){
    companion object{
        var instance:App by DelegatesExt.notNullSingleValue()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}