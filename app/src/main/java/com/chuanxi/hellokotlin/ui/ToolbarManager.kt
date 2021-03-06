package com.chuanxi.hellokotlin.ui

import android.support.v7.graphics.drawable.DrawerArrowDrawable
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.chuanxi.hellokotlin.BehaviorActivity
import com.chuanxi.hellokotlin.R
import com.chuanxi.hellokotlin.SettingsActivity
import com.chuanxi.hellokotlin.ctx
import com.chuanxi.hellokotlin.extensions.slideEnter
import com.chuanxi.hellokotlin.extensions.slideExit
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Created by tangjunjie on 2016/2/23.
 */
interface  ToolbarManager {
    val toolbar : Toolbar
    var toolbarTitle : String
            get() = toolbar.title.toString()
            set(value) {
                toolbar.title = value
            }

    fun initToolbar() {
        toolbar.inflateMenu(R.menu.menu_main)
        toolbar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.action_settings -> toolbar.ctx.startActivity<BehaviorActivity>()
                else -> App.instance.toast("Unknown option")
            }
            true
        }
    }

    fun enableHomeAsUp(up:()->Unit) {
        toolbar.navigationIcon = createUpDrawable()
        toolbar.setNavigationOnClickListener {
            up()
        }
    }

    fun createUpDrawable() = DrawerArrowDrawable(toolbar.ctx).apply {
        progress = 1f
    }

    fun attachToScroll(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if(dy > 0) toolbar.slideExit() else toolbar.slideEnter()
            }
        })
    }

}