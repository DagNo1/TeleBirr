package com.example.telebirr

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (supportActionBar != null) {
            supportActionBar?.hide()
        }
        setContentView(R.layout.activity_splash_screen)
        val sharedPreferences = getSharedPreferences("LoadUp", Context.MODE_PRIVATE)
        Handler().postDelayed({
            if (sharedPreferences.getBoolean("first_time",true)){
                val onBoarding = Intent(this,OnBoarding::class.java)
                startActivity(onBoarding)
            } else {
                val mainActivity = Intent(this, OnBoarding::class.java)
                startActivity(mainActivity)
            }
            finish()
        }, 2000)
    }
}