package com.chuanxi.hellokotlin.extensions

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.View

/**
 * Created by tangjunjie on 2016/2/23.
 */

public fun Context.color(res : Int):Int = ContextCompat.getColor(this,res)

fun View.slideExit() {
    if (translationY == 0f) animate().translationY(-height.toFloat())
}

fun View.slideEnter() {
    if (translationY < 0f) animate().translationY(0f)
}