package com.thssh.kotlin.service.api

import com.thssh.kotlin.data.bean.ForecastResult
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/07/17
 */
interface Api {
    companion object Factory{
        const val API_KEY = "250a9dbdcfc620263cb453f1b207a3e9"
        fun create():Api {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .connectTimeout(3_000, TimeUnit.MILLISECONDS)
                    .build()

            val retrofit = Retrofit.Builder()
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://api.openweathermap.org/")
                    .build()

            return retrofit.create(Api::class.java)
        }
    }

    @GET ("data/2.5/forecast/daily?units=metric&APPID=" + API_KEY)
    fun getData(@Query("q") cityName : String):Call<ForecastResult>
}