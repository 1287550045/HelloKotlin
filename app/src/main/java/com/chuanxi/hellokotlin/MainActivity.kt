package com.chuanxi.hellokotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.util.Log
import com.chuanxi.hellokotlin.domain.RequestForecastCommand
import com.chuanxi.hellokotlin.extensions.DelegatesExt
import com.chuanxi.hellokotlin.ui.ToolbarManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity(),ToolbarManager {

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    var zipCode: Long by DelegatesExt.longPreference(this, SettingsActivity.ZIP_CODE, SettingsActivity.DEFAULT_ZIP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()

        forecast_list.layoutManager = LinearLayoutManager(this)
        attachToScroll(forecast_list)
    }

    override fun onResume() {
        super.onResume()
        loadForecast()
    }

    private fun loadForecast()= async() {
        loge("zipCode = "+zipCode)
        val result = RequestForecastCommand(zipCode).execute()
        result.dailyForecast
        uiThread{
            forecast_list.adapter = ForecastListAdapter(result){
                startActivity<DetailActivity>(DetailActivity.ID to it.id,DetailActivity.CITY_NAME to result.city)
            }
            toolbarTitle = "${result.city} (${result.country})"
        }
    }

    public fun Context.loge(msg:String) {
        Log.e(this.javaClass.simpleName,msg)
    }

    fun <T> withIt(t : T, body : T.() -> Unit) {
        t.body()
    }

    class TypedClass<in T> {
        fun doSomething() {

        }
    }
}
