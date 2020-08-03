package com.zmj.funweather.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import com.zmj.funweather.FunWeatherApp
import com.zmj.funweather.logic.model.Place

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * GitHub : https://github.com/ZmjDns
 * Time : 2020/8/3
 * Description :
 */
object PlaceDao {

    fun savePlace(place: Place){
        sharedPreferences().edit{
            putString("place",Gson().toJson(place))
        }
    }

    fun getSavedPlace(): Place{
        val placeJson = sharedPreferences().getString("place","")
        return  Gson().fromJson(placeJson,Place::class.java)
    }

    fun isPlaceSaved() = sharedPreferences().contains("place")

    private fun sharedPreferences() = FunWeatherApp.getAppContext().getSharedPreferences("fun_weather",Context.MODE_PRIVATE)

}