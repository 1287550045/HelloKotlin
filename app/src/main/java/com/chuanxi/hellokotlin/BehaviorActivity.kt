package com.chuanxi.hellokotlin

import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.support.design.widget.SwipeDismissBehavior
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_behavior.*
import org.jetbrains.anko.onClick

/**
 * Created by tangjunjie on 2016/2/24.
 */
class BehaviorActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_behavior)

//        val swipe = SwipeDismissBehavior<View>()
//        swipe.setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_ANY)
//        swipe.setListener(object : SwipeDismissBehavior.OnDismissListener{
//            override fun onDismiss(v: View?) {
//            }
//
//            override fun onDragStateChanged(i: Int) {
//            }
//        })
//
//        (swipview.layoutParams as CoordinatorLayout.LayoutParams).behavior = swipe
//        fab.onClick {
//            Snackbar.make(it!!,"BehaviorActivity Snackbar is click!!",Snackbar.LENGTH_SHORT).show()
//        }
    }
}