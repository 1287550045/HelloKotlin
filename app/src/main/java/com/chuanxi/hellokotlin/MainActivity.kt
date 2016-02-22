package com.chuanxi.hellokotlin

import android.content.Context
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Toast
import com.chuanxi.hellokotlin.domain.Forecast
import com.chuanxi.hellokotlin.domain.RequestForecastCommand
import com.chuanxi.hellokotlin.ui.App
import junit.framework.Assert
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import org.jetbrains.anko.*
import java.util.*
import java.util.concurrent.Future

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(mtoolbar)
        mtoolbar.title = resources.getString(R.string.app_name)

        forecast_list.layoutManager = LinearLayoutManager(this)
        async() {
            val result = RequestForecastCommand(94043).execute()
            uiThread{
                forecast_list.adapter = ForecastListAdapter(result){
                    withIt(it) {
                        loge("date = ${date}")
                        loge("app = ${App.instance}")
                    }
                }
            }
        }
    }
    public fun Context.loge(msg:String) {
        Log.e(this.javaClass.simpleName,msg)
    }

    fun <T> withIt(t : T, body : T.() -> Unit) {
        t.body()
    }

}
