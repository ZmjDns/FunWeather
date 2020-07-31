package com.zmj.funweather

import android.app.Application
import android.content.Context

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * GitHub : https://github.com/ZmjDns
 * Time : 2020/7/31
 * Description :
 */
class FunWeatherApp : Application(){


    companion object{
        const val TOKEN = "Ocu8WKELANKroFOd"    //获取天气相关数据token
        private lateinit var mAppContext: Context
        fun getAppContext(): Context{
            return mAppContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        mAppContext = applicationContext
    }


}