package com.zmj.funweather.logic.network

import com.zmj.funweather.FunWeatherApp
import com.zmj.funweather.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * GitHub : https://github.com/ZmjDns
 * Time : 2020/7/31
 * Description :
 */
interface PlaceService {
    @GET("v2/place?token=${FunWeatherApp.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>
}