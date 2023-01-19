package com.example.telebirr

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = getSharedPreferences("LoadUp", Context.MODE_PRIVATE)
//        if (sharedPreferences.getBoolean("first_time",true)){
//            println("first_________Time")
//            val splashScreen = Intent(this,SplashScreen::class.java)
//            startActivity(splashScreen)
//            return;
//        }
        val splashScreen = Intent(this,OnBoarding::class.java)
            startActivity(splashScreen)
        setContentView(R.layout.activity_main)
    }
}