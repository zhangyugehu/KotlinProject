package com.thssh.kotlin.bean.data

import com.thssh.kotlin.data.bean.ForecastResult
import com.thssh.kotlin.data.Forecast
import com.thssh.kotlin.data.ForecastList
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.text.DateFormat
import java.util.*
import com.thssh.kotlin.data.bean.Forecast as InitalForecast

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/07/17
 */
class DataWrapper: AnkoLogger {

    fun convertFromDataToModel(forecastResult: ForecastResult): ForecastList {
        return ForecastList(forecastResult.city.id, forecastResult.city.name, forecastResult.city.country,
                covertList(forecastResult.city.name, forecastResult.list))
    }
    
    fun covertList(cityName:String, list:List<InitalForecast>):List<Forecast>{
        return list.map { convertForecastItemToModel(cityName, it) }
    }

    private fun convertForecastItemToModel(cityName: String, it: InitalForecast): Forecast {
        return Forecast(it.weather[0].id, convertData(it.dt), it.weather[0].description, it.temp.max.toInt(), it.temp.min.toInt(), generateIconUrl(it.weather[0].icon), cityName)
    }

    private fun convertData(dt: Long): String {
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return df.format(dt * 1000)
    }

    private fun generateIconUrl(icon: String): String {
        return "http://openweathermap.org/img/w/$icon.png"
    }
}