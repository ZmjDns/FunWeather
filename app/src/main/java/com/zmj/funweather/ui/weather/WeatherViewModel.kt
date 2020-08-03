package com.zmj.funweather.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.zmj.funweather.logic.Repository
import com.zmj.funweather.logic.model.Location

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * GitHub : https://github.com/ZmjDns
 * Time : 2020/7/31
 * Description :
 */
class WeatherViewModel : ViewModel() {

    private val locationLiveData = MutableLiveData<Location>()

    //3.数据缓存，防止屏幕旋转数据丢失
    var locationLng = ""
    var locationLat = ""
    var placeName = ""

    //2.执行网络请求
    val weatherLiveData = Transformations.switchMap(locationLiveData){ location ->
        Repository.refreshWeather(location.lng,location.lat)
    }

    //1.UI界面触发数据改变
    fun refreshWeather(lng: String,lat: String){
        locationLiveData.value = Location(lng, lat)
    }
}