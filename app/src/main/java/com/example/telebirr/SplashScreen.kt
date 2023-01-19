package com.example.telebirr

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = getSharedPreferences("LoadUp", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        setContentView(R.layout.activity_splash_screen)
        editor.apply {
            putBoolean("first_time",false);
            apply()
        }
        Handler().postDelayed({
            val intent = Intent(this, OnBoarding::class.java)
            startActivity(intent)
            finish()
        }, 4000)
    }
}