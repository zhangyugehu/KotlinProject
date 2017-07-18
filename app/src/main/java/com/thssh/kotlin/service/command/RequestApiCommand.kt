package com.thssh.kotlin.service.command

import com.thssh.kotlin.bean.data.DataWrapper
import com.thssh.kotlin.data.ForecastList
import com.thssh.kotlin.service.api.Api

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/07/17
 */
class RequestApiCommand(var cityName:String) : Command<ForecastList> {

    override fun execute(): ForecastList {
        val call = Api.Factory.create().getData(cityName)
        val result = call.execute()
        return DataWrapper().convertFromDataToModel(result.body()!!)
    }
}