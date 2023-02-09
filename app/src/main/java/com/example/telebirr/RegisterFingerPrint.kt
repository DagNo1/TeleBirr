package com.example.telebirr
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import com.example.telebirr.databinding.ActivityMainBinding

class RegisterFingerPrint : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (supportActionBar != null) {
            supportActionBar?.hide()
        }
        var login_method_is_password = true
        binding = ActivityMainBinding.inflate(layoutInflater)
        val sharedPreferences = getSharedPreferences("LoginMethod", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        if (!deviceHasBiometric()) {
            editor.apply {
                putBoolean("login_method_is_password",login_method_is_password)
                apply()
            }
            startActivity(Intent(this,RegistrationDone::class.java))
        }
        val next = findViewById<Button>(R.id.su_fp_next_button)
        setContentView(R.layout.activity_register_finger_print)
//        findViewById<LinearLayout>(R.id.linearLayout_in_fp).setOnClickListener {
//            findViewById<TextView>(R.id.textView_in_fp).setTextColor(Color.parseColor("#FF000000"))
//            next.isEnabled = true
//            next.setTextColor(Color.parseColor("#FFFFFFFF"))
//        }
//        findViewById<LinearLayout>(R.id.linearLayout1_in_fp).setOnClickListener {
//            findViewById<TextView>(R.id.textView1_in_fp).setTextColor(Color.parseColor("#FF000000"))
//            //TODO CHANGE OTHER'S COLOR
//            next.isEnabled = true
//            next.setTextColor(Color.parseColor("#FFFFFFFF"))
//            login_method_is_password = false
//        }
        next.setOnClickListener {
            editor.apply {
                putBoolean("login_method_is_password",login_method_is_password)
                apply()
            }
            startActivity(Intent(this,RegistrationDone::class.java))
        }
    }
    private fun deviceHasBiometric(): Boolean {
        val biometricManager = BiometricManager.from(this)
        return when (biometricManager.canAuthenticate(BIOMETRIC_STRONG or DEVICE_CREDENTIAL)) {
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE, BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                false
            }
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                // Prompts the user to create credentials that your app accepts.
                val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                    putExtra(Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                        BIOMETRIC_STRONG or DEVICE_CREDENTIAL)
                }
                startActivityForResult(enrollIntent, 100)
                true
            }
            else -> {
                true
                //stay on activity do nothing
            }
        }
    }
}
