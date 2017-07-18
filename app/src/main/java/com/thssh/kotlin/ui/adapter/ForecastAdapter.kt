package com.thssh.kotlin.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.thssh.kotlin.R
import com.thssh.kotlin.data.Forecast
import com.thssh.kotlin.data.ForecastList
import kotlinx.android.synthetic.main.item_forecast.view.*

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/07/17
 */
class ForecastAdapter(val weekForecast :ForecastList, val itemClick:(Forecast) -> (Unit)) : RecyclerView.Adapter<ForecastHolder>() {
    override fun onBindViewHolder(holder: ForecastHolder?, position: Int) {
        holder?.setData(weekForecast.dailyForecast[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ForecastHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_forecast, parent, false);
        return ForecastHolder(view, itemClick)
    }

    override fun getItemCount(): Int {
        return weekForecast.dailyForecast.size
    }

}

class ForecastHolder(itemView: View, val itemClick:(Forecast) -> (Unit)) : RecyclerView.ViewHolder(itemView) {

    fun setData(forecast : Forecast){
        with(forecast){
            Picasso.with(itemView.context)
                    .load(iconUrl)
                    .error(R.mipmap.ic_launcher)
                    .into(itemView.icon)
            itemView.icon.setImageResource(R.mipmap.ic_launcher_foreground)
            itemView.date_tv.text = date
            itemView.des_tv.text = descriptor
            itemView.temp_max_tv.text = "$hight °C"
            itemView.temp_min_tv.text = "$low °C"

            itemView.setOnClickListener{ itemClick(forecast) }
        }
    }
}
