package com.example.weatherman

import android.app.Application
import android.R

import io.github.inflationx.calligraphy3.CalligraphyConfig

import io.github.inflationx.calligraphy3.CalligraphyInterceptor

import io.github.inflationx.viewpump.ViewPump




class ConfigApplication() : Application() {
    override fun onCreate() {
        super.onCreate()
        ViewPump.init(
            ViewPump.builder()
                .addInterceptor(
                    CalligraphyInterceptor(
                        CalligraphyConfig.Builder()
                            .setDefaultFontPath("fonts/b_nazanin_bold.ttf")
                            .build()
                    )
                )
                .build()
        )
        //....
    }
}