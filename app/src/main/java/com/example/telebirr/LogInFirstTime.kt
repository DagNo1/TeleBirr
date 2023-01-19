package com.example.telebirr

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class LogInFirstTime : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (supportActionBar != null) {
            supportActionBar?.hide()
        }
        setContentView(R.layout.activity_log_in_first_time)
    }
}