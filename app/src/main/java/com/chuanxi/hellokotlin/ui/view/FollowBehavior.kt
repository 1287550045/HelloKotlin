package com.chuanxi.hellokotlin.ui.view

import android.content.Context
import android.content.res.TypedArray
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.view.View
import com.chuanxi.hellokotlin.R

/**
 * Created by tangjunjie on 2016/2/25.
 */
class FollowBehavior<T:View> : CoordinatorLayout.Behavior<T> {
    private var targetId:Int = -1
    constructor() : super() {

    }

    constructor(context:Context , attrs: AttributeSet) :super(context,attrs) {
        val a : TypedArray = context.obtainStyledAttributes(attrs, R.styleable.Follow)
        for(i in 0..a.indexCount-1) {
            val attr = a.getIndex(i)
            if(a.getIndex(i)==R.styleable.Follow_target) {
                targetId = a.getResourceId(attr,-1)
            }
        }
        a.recycle()
    }

    override fun layoutDependsOn(parent: CoordinatorLayout?, child: T, dependency: View?): Boolean {
        return dependency!!.id==targetId
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout?, child: T, dependency: View?): Boolean {
        child.y = dependency!!.y+dependency!!.height
        return true
    }

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout?, child: T, directTargetChild: View?, target: View?, nestedScrollAxes: Int): Boolean {
        return true;//这里返回true，才会接受到后续滑动事件。
    }

    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout?, child: T, target: View?, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int) {
        //进行滑动事件处理
    }

    override fun onNestedFling(coordinatorLayout: CoordinatorLayout?, child: T, target: View?, velocityX: Float, velocityY: Float, consumed: Boolean): Boolean {
        //当进行快速滑动
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed)
    }
}