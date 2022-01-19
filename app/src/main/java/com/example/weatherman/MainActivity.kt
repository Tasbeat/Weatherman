package com.example.weatherman

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.ClipData
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.weatherman.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase!!))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        var badge = binding.bottomNavigation.getOrCreateBadge(R.id.page1)
        badge.isVisible = true
        badge.number = 99

        binding.bottomNavigation.setOnNavigationItemReselectedListener { item ->
            when (item.itemId) {
                R.id.page1 -> {
                    Toast.makeText(this, "weather page", Toast.LENGTH_SHORT).show()

                }
                R.id.page2 -> {

                }
            }
        }
        binding.bottomNavigation.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.page1 -> {

                    true
                }
                R.id.page2 -> {
                    Toast.makeText(this, "page 2 ", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        })

    }
}