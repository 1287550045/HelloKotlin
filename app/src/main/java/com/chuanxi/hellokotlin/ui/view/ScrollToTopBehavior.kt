package com.chuanxi.hellokotlin.ui.view

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.view.View
import java.util.jar.Attributes

/**
 * Created by tangjunjie on 2016/2/25.
 */
class ScrollToTopBehavior<T : View> : CoordinatorLayout.Behavior<T> {
    var offsetTotal = 0;
    var scrolling = false;
    constructor() : super() {

    }

    constructor(context: Context,attrs: AttributeSet) :super(context,attrs) {

    }

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout?, child: T, directTargetChild: View?, target: View?, nestedScrollAxes: Int): Boolean {
        return super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes)
    }

    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout?, child: T, target: View?, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed)
    }
    fun  offset( child:View, dy:Int){
        var old:Int = offsetTotal
        var top:Int = offsetTotal - dy
        top = Math.max(top, -child.getHeight())
        top = Math.min(top, 0)
        offsetTotal = top
        if (old == offsetTotal){
            scrolling = false
            return;
        }
        var delta = offsetTotal-old
        child.offsetTopAndBottom(delta)
        scrolling = true
    }

}