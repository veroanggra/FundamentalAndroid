package com.veronica.idn.viewmodelsecond

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.lang.Exception
import java.text.DecimalFormat

class MainViewModel : ViewModel() {
    val listWeather = MutableLiveData<ArrayList<WeatherItems>>()

    fun setWeather(cities: String) {
        val listItems = ArrayList<WeatherItems>()
        val apiKey = " "
        val url =
            "https://api.openweathermap.org/data/2.5/group?id=${cities}&units=metric&appid=${apiKey}"
        val client = AsyncHttpClient()
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>,
                responseBody: ByteArray
            ) {
                try {
                    val result = String((responseBody))
                    val responseObject = JSONObject(result)
                    val list = responseObject.getJSONArray("list")

                    for (i in 0 until list.length()) {
                        val weather = list.getJSONObject(i)
                        val weatherItems = WeatherItems()
                        weatherItems.id = weather.getInt("id")
                        weatherItems.name = weather.getString("name")
                        weatherItems.currentWeather =
                            weather.getJSONArray("weather").getJSONObject(0).getString("main")
                        weatherItems.description = weather.getJSONArray("weather").getJSONObject(0)
                            .getString("descriptions")
                        val tempInKelvin = weather.getJSONObject("main").getDouble("temp")
                        val tempInCelcius = tempInKelvin - 273
                        weatherItems.temperature = DecimalFormat("##.##").format(tempInCelcius)
                        listItems.add(weatherItems)
                    }
                    listWeather.postValue(listItems)
                } catch (e: Exception) {
                    Log.d("Exception", e.message.toString())
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>,
                responseBody: ByteArray,
                error: Throwable
            ) {
                Log.d("onFailure", error.message.toString())
            }

        })


    }

    fun getWeather(): LiveData<ArrayList<WeatherItems>> {
        return listWeather
    }
}