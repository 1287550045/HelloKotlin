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

//        val forecast_list:RecyclerView = find(R.id.forecast_list)
        forecast_list.layoutManager = LinearLayoutManager(this)
        async() {
            val result = RequestForecastCommand("94043").execute()
            uiThread{
                forecast_list.adapter = ForecastListAdapter(result){
                    withIt(it) {
                        loge("date = ${date}")
                    }
                }
            }
        }
//        val f1 = Forecast(Date(),25.6f,"test")
//        val f2 = f1.copy(temperature = 28.9f)
//        loge("f2.temperature = ${f2.temperature}")
//        val (date,temperature,details) = f2
//        loge("date=$date,temperature=$temperature,details=$details")
    }
    public fun Context.loge(msg:String) {
        Log.e(this.javaClass.simpleName,msg)
    }

    fun <T> withIt(t : T, body : T.() -> Unit) {
        t.body()
    }

}
