package com.example.telebirr

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import me.relex.circleindicator.CircleIndicator3


class OnBoarding : AppCompatActivity() {
    private val imagesList = mutableListOf<Int>(R.drawable.safesvg_1,R.drawable.safesvg_2,R.drawable.safesvg_3)
    private val headerTextList = mutableListOf<String>("Secure","Blazing-ly Fast","Pay Bills")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (supportActionBar != null) {
            supportActionBar?.hide()
        }
        setContentView(R.layout.activity_on_boarding)
//        findViewById<Button>(R.id.button).setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }
        findViewById<ViewPager2>(R.id.viewPager2).adapter = SlideAdapter(imagesList,headerTextList)
        findViewById<ViewPager2>(R.id.viewPager2).orientation = ViewPager2.ORIENTATION_HORIZONTAL
        val indicator: CircleIndicator3 = findViewById(R.id.indicator)
        indicator.setViewPager(findViewById(R.id.viewPager2))

        val sharedPreferences = getSharedPreferences("LoadUp", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply {
            putBoolean("first_time",false)
            apply()
        }

        val registerText: TextView = findViewById(R.id.ob_register_button)
        val logInBtn: Button = findViewById(R.id.ob_login_button)

        registerText.setOnClickListener {
            startActivity(Intent(this,RegisterPhoneVerification::class.java))
        }
        logInBtn.setOnClickListener {
            startActivity(Intent(this,LogInFirstTime::class.java))
        }
    }
    override fun onBackPressed() {
        val sharedPreferences = getSharedPreferences("LoadUp", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply {
            putBoolean("first_time",true)
            apply()
        }
        finishAffinity()
    }
}