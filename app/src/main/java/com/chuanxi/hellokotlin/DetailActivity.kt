package com.chuanxi.hellokotlin

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.antonioleiva.weatherapp.extensions.toDateString
import com.chuanxi.hellokotlin.domain.Forecast
import com.chuanxi.hellokotlin.domain.RequestDayForecastCommand
import com.squareup.picasso.Picasso
import org.jetbrains.anko.async
import org.jetbrains.anko.uiThread
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.ctx
import java.text.DateFormat

/**
 * Created by tangjunjie on 2016/2/22.
 */
class DetailActivity : AppCompatActivity(){

    companion object {
        val ID = "DetailActivity:id"
        val CITY_NAME = "DetailActivity.cityName"
    }
    var title:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        title = intent.getStringExtra(CITY_NAME)

        async() {
            val result = RequestDayForecastCommand(intent.getLongExtra(ID,-1)).execute()
            uiThread {

            }
        }
    }

    fun bindForecast(forecast: Forecast) = with(forecast) {
        Picasso.with(ctx).load(iconUrl).into(icon)
        setSupportActionBar(mtoolbar)
        mtoolbar.title = title
        mtoolbar.subtitle = date.toDateString(DateFormat.FULL)
        weatherDescription.text = description
    }
}