package com.example.telebirr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import me.relex.circleindicator.CircleIndicator3

class OnBoarding : AppCompatActivity() {
    private val imagesList = mutableListOf<Int>(R.drawable.safesvg_1,R.drawable.safesvg_2,R.drawable.safesvg_3)
    private val headerTextList = mutableListOf<String>("Secure","Blazing-ly Fast","Pay Bills")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)
//        findViewById<Button>(R.id.button).setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }
        findViewById<ViewPager2>(R.id.viewPager2).adapter = SlideAdapter(imagesList,headerTextList)
        findViewById<ViewPager2>(R.id.viewPager2).orientation = ViewPager2.ORIENTATION_HORIZONTAL
        val indicator: CircleIndicator3 = findViewById(R.id.indicator)
        indicator.setViewPager(findViewById(R.id.viewPager2))
    }
}