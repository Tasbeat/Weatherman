package com.example.weatherman

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import com.example.weatherman.databinding.ActivityMainBinding
import okhttp3.*
import org.json.JSONObject
import java.io.IOException


class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        var client = OkHttpClient()

        var request = Request.Builder().url("https://api.openweathermap.org/data/2.5/weather?q=tehran&appid=a1a3593bda00a8c6ef840d3f50446c8e&lang=fa&units=metric").build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                val responseRawString = response.body!!.string()
                val jsonObject = JSONObject(responseRawString)
                //coord
                val coord = jsonObject.getJSONObject("coord")
                val lon = coord.getDouble("lon")
                val lat = coord.getDouble("lat")
                //weather
                val weather = jsonObject.getJSONArray("weather")
                val weather1 = weather.getJSONObject(0)
                val description = weather1.getString("description")
                //main
                val main = jsonObject.getJSONObject("main")
                val temp = main.getDouble("temp")
                val feelsLike = main.getDouble("feels_like")
                val tempMin = main.getDouble("temp_min")
                val tempMax = main.getDouble("temp_max")
                val pressure = main.getInt("pressure")
                val humidity = main.getInt("humidity")

                val icon = weather1.getString("icon")
                val iconPatternUrl = "https://openweathermap.org/img/wn/$icon@2x.png"
                runOnUiThread{
                    Glide.with(this@MainActivity).load(iconPatternUrl).into(binding.humidityIcon)
                }
            }
        })
    }
}