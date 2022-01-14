package com.example.weatherman

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.weatherman.databinding.ActivityMainBinding
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        callAndGetDataFromApi()

    }

    fun getData(jsonObject: JSONObject) {
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
        //sys
        val sys = jsonObject.getJSONObject("sys")
        val sunrise = sys.getInt("sunrise")
        val sunset = sys.getInt("sunset")
        //wind
        val wind = jsonObject.getJSONObject("wind")
        val windSpeed = wind.getDouble("speed")
        val windDegrees = wind.getDouble("deg") //Wind direction, degrees (meteorological)

        val icon = weather1.getString("icon")
        val iconPatternUrl = "https://openweathermap.org/img/wn/$icon@2x.png"
        runOnUiThread {
            Glide.with(this@MainActivity).load(iconPatternUrl).into(binding.weatherIcon)
            binding.temprautureShow.text = temp.toString()
            binding.descriptionTextView.text = description
            binding.sunriseTextView.text = convertUnixTimeToCurrent(sunrise)
            binding.sunsetTextView.text = convertUnixTimeToCurrent(sunset)
            binding.windSpeedAndDirectionTextView.text = " $windDegrees  درجه /               $windSpeed  متر بر ثانیه  "
            binding.progressBarWeather.visibility = View.INVISIBLE
            binding.weatherIcon.visibility = View.VISIBLE
            binding.temperatureSensedTextView.text = feelsLike.toString()
            binding.pressureTextView.text = "${pressure.toString()} هکتو پاسکال"
            binding.humidityTextView.text = humidity.toString()

        }
    }
     fun callAndGetDataFromApi(){
        var client = OkHttpClient()

        var request = Request.Builder()
            .url("https://api.openweathermap.org/data/2.5/weather?q=tehran&appid=a1a3593bda00a8c6ef840d3f50446c8e&lang=fa&units=metric")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                val responseRawString = response.body!!.string()
                val jsonObject = JSONObject(responseRawString)
                getData(jsonObject)
            }
        })
    }
    fun reload(view : View){
        view as ImageView
        //invisible current Icons
        binding.humidityIcon.visibility = View.INVISIBLE
        binding.weatherIcon.visibility = View.INVISIBLE
        binding.meghdareBaareshIcon.visibility = View.INVISIBLE
        binding.pressureIcon.visibility = View.INVISIBLE
        binding.sunriseIcon.visibility = View.INVISIBLE
        binding.sunsetIcon.visibility = View.INVISIBLE
        binding.vazeshBaadIcon.visibility = View.INVISIBLE
        binding.temperatureSensedIcon.visibility = View.INVISIBLE
        binding.windSpeedAndDirectionIcon.visibility = View.INVISIBLE
        //visible progress Icons for each Item
        binding.progressBarHumidity.visibility = View.VISIBLE
        binding.progressBarPressure.visibility = View.VISIBLE
        binding.progressBarRain.visibility = View.VISIBLE
        binding.progressBarSunrise.visibility = View.VISIBLE
        binding.progressBarSunset.visibility = View.VISIBLE
        binding.progressBarTemp.visibility = View.VISIBLE
        binding.progressBarWeather.visibility = View.VISIBLE
        binding.progressBarWind.visibility = View.VISIBLE
        binding.progressBarWindDirect.visibility = View.VISIBLE
        //Text Views in reload mode
        binding.humidityTextView.text = "..."
        binding.meghdareBaareshTextView.text = "..."
        binding.pressureTextView.text = "..."
        binding.sunriseTextView.text = "..."
        binding.sunsetTextView.text = "..."
        binding.temperatureSensedTextView.text = "..."
        binding.vazeshBaadTextView.text = "..."
        binding.windSpeedAndDirectionTextView.text = "..."
        binding.temprautureShow.text = "..."
        binding.descriptionTextView.text = "..."


        callAndGetDataFromApi()
        resetVisibilityOfItems()


    }
    private fun convertUnixTimeToCurrent(unixTime : Int):String{
        val unixTimeInMilliSecond =  (unixTime * 1000).toLong()
        val date = Date(unixTimeInMilliSecond)
        val formatter = SimpleDateFormat("HH:mm aa")
        return formatter.format(date)
    }
    fun resetVisibilityOfItems(){
        //Icons
        binding.humidityIcon.visibility = View.VISIBLE
        binding.meghdareBaareshIcon.visibility = View.VISIBLE
        binding.pressureIcon.visibility = View.VISIBLE
        binding.sunriseIcon.visibility = View.VISIBLE
        binding.sunsetIcon.visibility = View.VISIBLE
        binding.vazeshBaadIcon.visibility = View.VISIBLE
        binding.temperatureSensedIcon.visibility = View.VISIBLE
        binding.windSpeedAndDirectionIcon.visibility = View.VISIBLE
        //Progress Bars
        binding.progressBarHumidity.visibility = View.INVISIBLE
        binding.progressBarPressure.visibility = View.INVISIBLE
        binding.progressBarRain.visibility = View.INVISIBLE
        binding.progressBarSunrise.visibility = View.INVISIBLE
        binding.progressBarSunset.visibility = View.INVISIBLE
        binding.progressBarTemp.visibility = View.INVISIBLE
        binding.progressBarWind.visibility = View.INVISIBLE
        binding.progressBarWindDirect.visibility = View.INVISIBLE
    }
}