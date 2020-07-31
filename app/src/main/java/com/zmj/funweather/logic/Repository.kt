package com.zmj.funweather.logic

import androidx.lifecycle.liveData
import com.zmj.funweather.logic.model.Place
import com.zmj.funweather.logic.network.FunWeatherNetWork
import kotlinx.coroutines.Dispatchers

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * GitHub : https://github.com/ZmjDns
 * Time : 2020/7/31
 * Description : repository层相当于获取数据与缓存的中间层
 * 判断数据是本地取出还是远程网络取
 */
object Repository {
    fun searchPlaces(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            val placeResponse = FunWeatherNetWork.searchPlace(query)

            if (placeResponse.status == "ok"){
                val places = placeResponse.places
                Result.success(places)
            }else{
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }
        }catch (e: Exception){
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }
}