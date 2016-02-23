package com.chuanxi.hellokotlin

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.TextView
import com.antonioleiva.weatherapp.extensions.toDateString
import com.chuanxi.hellokotlin.domain.Forecast
import com.chuanxi.hellokotlin.domain.RequestDayForecastCommand
import com.chuanxi.hellokotlin.extensions.color
import com.chuanxi.hellokotlin.ui.ToolbarManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.*
import java.text.DateFormat

/**
 * Created by tangjunjie on 2016/2/22.
 */
class DetailActivity : AppCompatActivity(), ToolbarManager {
    companion object {
        val ID = "DetailActivity:id"
        val CITY_NAME = "DetailActivity.cityName"
    }
    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        initToolbar()
        toolbarTitle = intent.getStringExtra(CITY_NAME)
        enableHomeAsUp {
            onBackPressed()
        }
        async() {
            val result = RequestDayForecastCommand(intent.getLongExtra(ID,-1)).execute()
            uiThread {
                bindForecast(result)
            }
        }
    }

    fun bindForecast(forecast: Forecast) = with(forecast) {
        Picasso.with(ctx).load(iconUrl).into(icon)
        weatherDescription.text = description
        bindWeather(high to maxTemperature,low to minTemperature)
    }

    private fun bindWeather(vararg views:Pair<Int,TextView>) = views.forEach {
        it.second.text = "${it.first.toString()}℃"
        it.second.textColor = color(when(it.first){
            in -50..0 -> android.R.color.holo_red_dark
            in 0..15 -> android.R.color.holo_orange_dark
            else -> android.R.color.holo_green_dark
        })
    }
}