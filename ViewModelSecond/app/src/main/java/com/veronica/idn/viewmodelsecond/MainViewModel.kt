package com.veronica.idn.viewmodelsecond

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val listWeather = MutableLiveData<ArrayList<WeatherItems>>()
    fun setWeather (cities : String){
        val listItems = ArrayList<WeatherItems>()


    }
    fun getWeather() : LiveData<ArrayList<WeatherItems>>{
        return  listWeather
    }
}