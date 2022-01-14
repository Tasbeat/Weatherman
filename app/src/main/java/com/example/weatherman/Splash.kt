package com.example.weatherman

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)
        loadSplashScreen()
        RunAnimation()
    }

    fun loadSplashScreen() {
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 6000)
    }

    fun RunAnimation() {
        val a = AnimationUtils.loadAnimation(this, R.anim.linear_interpolator)
        a.reset()
        val tv = findViewById<View>(R.id.textView) as TextView
        tv.clearAnimation()
        tv.startAnimation(a)
    }
}
