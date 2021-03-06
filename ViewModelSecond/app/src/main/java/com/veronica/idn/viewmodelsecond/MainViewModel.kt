package com.veronica.idn.viewmodelsecond

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val listWeather = MutableLiveData<ArrayList<WeatherItems>>()
}