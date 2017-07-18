package com.thssh.kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.thssh.kotlin.service.command.RequestApiCommand
import com.thssh.kotlin.service.command.RequestCommand
import com.thssh.kotlin.ui.adapter.ForecastAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity(), AnkoLogger{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        forecastlist.layoutManager =  LinearLayoutManager(this)

        refreshData()
    }

    fun toast(text: String){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    fun refreshData(){
        async {
            // kotlin 协程：调度不是由操作系统决定。妥协、退让的调度  yield suspuend
            // java 线程：调度是由操作系统决定的。抢占式调度
//            val result = RequestCommand("Shenzhen").execute()

            try {

                val result = RequestApiCommand("beijing").execute()

                info("adapter ${result.dailyForecast.size}")

                uiThread {
                    forecastlist.adapter = ForecastAdapter(result){
                        it -> toast(it.cityName)
                    }
                }// ?end of uiThread?
            }catch (e : Exception){
                println(e.toString())
            }
        }// ?end of async
    }
}
