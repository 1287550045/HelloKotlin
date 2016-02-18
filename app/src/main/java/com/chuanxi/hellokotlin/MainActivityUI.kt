package com.chuanxi.hellokotlin

import android.view.View
import org.jetbrains.anko.*

/**
 * Created by tangjunjie on 2016/2/17.
 */
class MainActivityUI :AnkoComponent<MainActivity> {

    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        verticalLayout {
            padding = dip(16)
            val name = editText()
            button("Say Hello") {
                onClick { ctx.toast("Hello, ${name.text}!") }
            }

            linearLayout {
                button("ok") {
                    lparams{
                        width = dip(0)
                        weight = 1.0f
                        height = dip(40)
                    }
                }
                button("cancel") {

                }.lparams(dip(0),wrapContent) {
                    weight = 1.0f
                }.onClick {
                    ctx.toast("Hello ....")
                }
            }
        }
    }
}