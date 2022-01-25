package com.example.weatherman

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherman.databinding.FragmentMusicSearchBinding
import com.example.weatherman.databinding.FragmentWeatherBinding
import okhttp3.*
import org.json.JSONObject
import java.io.IOException


class MusicSearchFragment : Fragment() {
    private var _binding: FragmentMusicSearchBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMusicSearchBinding.inflate(inflater, container, false)
        val view = binding.root
        //
        binding.analogClockView.setTimeZone("Asia/Tehran" )

        return view
    }
    /*fun callAndGetDataFromApi() {
        var client = OkHttpClient()

        var request = Request.Builder()
            .url("https://api.codebazan.ir/time-date/?json=all")
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

    private fun getData(jsonObject: JSONObject) {
        val result = jsonObject.getJSONObject("result")
        var second = result.getInt("sec")
        var minutes = result.getInt("min")
        var hour = result.getInt("hour")

        activity?.runOnUiThread {

        }

    }*/

}