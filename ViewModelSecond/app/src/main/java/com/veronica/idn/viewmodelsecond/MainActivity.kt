package com.veronica.idn.viewmodelsecond

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.veronica.idn.viewmodelsecond.databinding.ActivityMainBinding
import cz.msebera.android.httpclient.Header
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.lang.Exception
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: WeatherAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = WeatherAdapter()
        adapter.notifyDataSetChanged()
        binding.rvMain.layoutManager = LinearLayoutManager(this)
        binding.rvMain.adapter = adapter
        binding.btnCity.setOnClickListener {
            val city = et_city.text.toString()
            if (city.isEmpty())
                return@setOnClickListener
            showloading(true)
            setWeather(city)

        }

    }

    private fun setWeather(city: String) {
        val listItem = ArrayList<WeatherItems>()
        val apiKey = "0e336d04ff15c62726b4224d2457f149"
        val url =
            "https://api.openweathermap.org/data/2.5/group?id=${city}&units=metric&appid=${apiKey}"
        val client = AsyncHttpClient()
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>,
                responseBody: ByteArray
            ) {
                try {
                    val result = String(responseBody)
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
                            .getString("description")
                        val tempInKelvin = weather.getJSONObject("main").getDouble("temp")
                        val tempInCelcius = tempInKelvin - 273
                        weatherItems.temperature = DecimalFormat("##.##").format(tempInCelcius)
                        listItem.add(weatherItems)

                    }
                    adapter.setData(listItem)
                    showloading(false)
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

    private fun showloading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}