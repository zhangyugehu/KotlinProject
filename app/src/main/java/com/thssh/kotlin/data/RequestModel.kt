package com.thssh.kotlin.data

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/07/17
 */

data class Forecast(val id:Long, val date:String, val descriptor:String, val hight:Int, val low:Int, val iconUrl:String, val cityName:String)

data class ForecastList(val _id:Long, val city:String, val country:String, val dailyForecast:List<Forecast>)