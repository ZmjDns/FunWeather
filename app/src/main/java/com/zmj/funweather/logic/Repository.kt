package com.zmj.funweather.logic

import androidx.lifecycle.liveData
import com.zmj.funweather.logic.dao.PlaceDao
import com.zmj.funweather.logic.model.Place
import com.zmj.funweather.logic.model.Weather
import com.zmj.funweather.logic.network.FunWeatherNetWork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.CoroutineContext

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * GitHub : https://github.com/ZmjDns
 * Time : 2020/7/31
 * Description : repository层相当于获取数据与缓存的中间层
 * 判断数据是本地取出还是远程网络取
 */
object Repository {
    /*此处代码跟下面优化过的代码执行同样的功能
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

    fun refreshWeather(lng: String,lat: String) = liveData(Dispatchers.IO) {
        val result = try {
            coroutineScope {
                val deferredRealtime = async { FunWeatherNetWork.getRealtimeWeather(lng, lat) }
                val deferredDaily = async { FunWeatherNetWork.getDailyWeather(lng, lat) }
                val realTimeResponse = deferredRealtime.await()
                val dailyResponse = deferredDaily.await()

                if (realTimeResponse.status == "ok" && dailyResponse.status == "ok"){
                    val weather = Weather(realTimeResponse.result.realTime,dailyResponse.result.daily)
                    Result.success(weather)
                }else{
                    Result.failure(
                        RuntimeException(
                            "realtime response ststus is ${realTimeResponse.status}\n " +
                            "daily response is ${dailyResponse.status}"
                        )
                    )
                }
            }
        }catch (e: Exception){
            Result.failure<Weather>(e)
        }
        emit(result)
    }*/

    fun searchPlaces(query: String) = fire(Dispatchers.IO) {
        val placeResponse = FunWeatherNetWork.searchPlace(query)
        if (placeResponse.status == "ok"){
            val places = placeResponse.places
            Result.success(places)
        }else{
            Result.failure(RuntimeException("response status is ${placeResponse.status}"))
        }
    }

    fun refreshWeather(lng: String,lat: String) = fire(Dispatchers.IO){
        coroutineScope {
            val deferredRealtime = async { FunWeatherNetWork.getRealtimeWeather(lng, lat) }
            val deferredDaily = async { FunWeatherNetWork.getDailyWeather(lng, lat) }
            val realTimeResponse = deferredRealtime.await()
            val dailyResponse = deferredDaily.await()
            if (realTimeResponse.status == "ok" && dailyResponse.status == "ok"){
                val weather = Weather(realTimeResponse.result.realtime,dailyResponse.result.daily)
                Result.success(weather)
            }else{
                Result.failure(
                    RuntimeException(
                        "realtime response ststus is ${realTimeResponse.status}\n " +
                        "daily response is ${dailyResponse.status}"
                    )
                )
            }
        }
    }

    /**
     * 按照liveData（）函数的参数接收标准定义的一个高阶函数
     */
    private fun <T> fire(context: CoroutineContext,block: suspend() ->Result<T>) =
        liveData<Result<T>>(context) {
            val result = try {
                block()
            }catch (e: Exception){
                Result.failure<T>(e)
            }
            emit(result)
        }

    fun savePlace(place: Place) = PlaceDao.savePlace(place)

    fun getSavedPlace() = PlaceDao.getSavedPlace()

    fun isPlaceSaved() = PlaceDao.isPlaceSaved()

}