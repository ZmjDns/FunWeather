package com.zmj.funweather.logic.model

/**
 * Author : Zmj
 * Blog : https://blog.csdn.net/Zmj_Dns
 * GitHub : https://github.com/ZmjDns
 * Time : 2020/7/31
 * Description :
 */
data class Weather(val realtime: RealTimeResponse.RealTime,val daily: DailyResponse.Daily)