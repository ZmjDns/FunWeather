package com.zmj.funweather.logic.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * GitHub : https://github.com/ZmjDns
 * Time : 2020/7/31
 * Description :
 */
object FunWeatherNetWork {

    /**
     * 获取各地区名称接口
     * private val placeService = ServiceCreator.create(PlaceService::class.java)
     */
    private val placeService = ServiceCreator.create<PlaceService>()    //kotlin泛型实化

    /**
     * 获取天气信息接口
     */
    private val weatherService = ServiceCreator.create<WeatherService>()

    //执行网络请求获取城市信息
    suspend fun searchPlace(query: String) = placeService.searchPlaces(query).await()

    suspend fun getRealtimeWeather(lng: String,lat: String) = weatherService.getRealtimeWeather(lng, lat).await()

    suspend fun getDailyWeather(lng: String,lat: String) = weatherService.getDailyWeather(lng, lat).await()


    /**
     * 统一的对网络返回数据处理
     */
    private suspend fun <T>Call<T>.await():T {
        return suspendCoroutine { continuation ->
            enqueue(object :Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) {
                        continuation.resume(body)
                    }else{
                        continuation.resumeWithException(RuntimeException("response body is null"))
                    }
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }

    }

}