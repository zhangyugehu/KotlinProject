package com.thssh.kotlin.service.command

import com.google.gson.Gson
import com.thssh.kotlin.bean.data.DataWrapper
import com.thssh.kotlin.data.ForecastList
import com.thssh.kotlin.data.bean.ForecastResult
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.net.URL

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/07/17
 */
class RequestCommand(var cityName:String) : Command<ForecastList>, AnkoLogger {

    companion object {
        private val APP_ID = "250a9dbdcfc620263cb453f1b207a3e9"
        private val URI = "http://api.openweathermap.org/data/2.5/forecast/daily?"
        private val COMPLETE_URL = "${URI}&APPID=$APP_ID&q="
    }

    override fun execute(): ForecastList {
        val url = "$COMPLETE_URL$cityName"

        info("RequestCommand url = $url")

        val forecastJsonStr = URL(url).readText()
        val forecastResult = Gson().fromJson(forecastJsonStr, ForecastResult::class.java)

        val model = DataWrapper().convertFromDataToModel(forecastResult)

        return model
    }
}