package com.zmj.funweather.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.zmj.funweather.logic.Repository
import com.zmj.funweather.logic.model.Place

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * GitHub : https://github.com/ZmjDns
 * Time : 2020/7/31
 * Description :  viewModel相当于逻辑与UI之间的桥梁，
 * 虽然它更偏向于逻辑层部分，但由于View Model通常和Activity、Fragment 一一对应的，
 * 因此我们还是习惯将它们放在一起
 */
class PlaceViewModel : ViewModel(){

    private val searchLiveData = MutableLiveData<String>()

    /**
     * 用于对获取的在界面上显示的城市数据进行缓存
     * 因为原则上与界面相关的数据都应该放在ViewModel中，以保证屏幕发生旋转数据不会丢失
     * 稍后界面会用到这个数据
     */
    val placeList = ArrayList<Place>()

    /**
     * 在UI中观察此对象
     */
    val placeLiveData = Transformations.switchMap(searchLiveData){ query ->
        Repository.searchPlaces(query)
    }
    /**
     * searchLiveData改变触发Transformations.switchMap观察searchLiveData
     */
    fun searchPlaces(query: String){
        searchLiveData.value = query
    }

}