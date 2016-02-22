package com.chuanxi.hellokotlin.data.server

import com.chuanxi.hellokotlin.db.ForecastDb
import com.chuanxi.hellokotlin.domain.Forecast
import com.chuanxi.hellokotlin.domain.ForecastList
import com.chuanxi.hellokotlin.domain.ServerDataMapper
import com.chuanxi.hellokotlin.domain.datasource.ForecastDataSource

/**
 * Created by tangjunjie on 2016/2/22.
 */
class ForecastServer(val dataMapper: ServerDataMapper = ServerDataMapper(),
                     val forecaseDb: ForecastDb = ForecastDb()) : ForecastDataSource {
    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        val result = ForecastByZipCodeRequest(zipCode).execute()
        val converted = dataMapper.convertToDomain(zipCode,result)
        forecaseDb.saveForecastList(converted)
        return forecaseDb.requestForecastByZipCode(zipCode,date)
    }

    override fun requestDayForecast(id: Long): Forecast? {
        throw UnsupportedOperationException()
    }
}