package com.zmj.funweather.logic.network

import com.zmj.funweather.FunWeatherApp
import com.zmj.funweather.logic.model.DailyResponse
import com.zmj.funweather.logic.model.RealTimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * GitHub : https://github.com/ZmjDns
 * Time : 2020/7/31
 * Description :
 */
interface WeatherService {
    //实时天气信息
    @GET("v2.5/${FunWeatherApp.TOKEN}/{lng},{lat}/realtime.json")
    fun getRealtimeWeather(@Path("lng") lng: String,@Path("lat") lat: String): Call<RealTimeResponse>

    //未来天天信息
    @GET("v2.5/${FunWeatherApp.TOKEN}/{lng},{lat}/daily.json")
    fun getDailyWeather(@Path("lng") lng: String,@Path("lat") lat: String): Call<DailyResponse>
}