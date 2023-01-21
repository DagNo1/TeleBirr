package com.example.telebirr

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

class CreateAccount : AppCompatActivity() {
    private lateinit var fName: EditText
    private lateinit var mName: EditText
    private lateinit var lName: EditText
    private lateinit var pass: EditText
    private lateinit var cpass: EditText
    private lateinit var male: RadioButton
    private lateinit var female: RadioButton
    private lateinit var next: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)
        fName = findViewById(R.id.first_name_input_in_ca)
        mName = findViewById(R.id.middle_name_input_in_ca)
        lName = findViewById(R.id.last_name_input_in_ca)
        pass = findViewById(R.id.password_input_in_ca)
        cpass = findViewById(R.id.c_password_input_in_ca)
        male = findViewById(R.id.radioButton_male_in_ca)
        female = findViewById(R.id.radioButton_female_in_ca)
        next = findViewById(R.id.su_ca_next_button)


        fName.addTextChangedListener(SignUpTextWatcher(fName,mName,lName,pass,cpass,male,female,next))
        mName.addTextChangedListener(SignUpTextWatcher(fName,mName,lName,pass,cpass,male,female,next))
        lName.addTextChangedListener(SignUpTextWatcher(fName,mName,lName,pass,cpass,male,female,next))
        pass.addTextChangedListener(SignUpTextWatcher(fName,mName,lName,pass,cpass,male,female,next))
        cpass.addTextChangedListener(SignUpTextWatcher(fName,mName,lName,pass,cpass,male,female,next))
        male.setOnCheckedChangeListener { _, _ ->
            if (formFiled()){
                next.isEnabled = true
                next.setTextColor(Color.parseColor("#FFFFFFFF"))
                return@setOnCheckedChangeListener
            }
            next.isEnabled = false
            next.setTextColor(Color.parseColor("#777474"))

        }
        female.setOnCheckedChangeListener { _, _ ->
            if (formFiled()){
                next.isEnabled = true
                next.setTextColor(Color.parseColor("#FFFFFFFF"))
                return@setOnCheckedChangeListener
            }
            next.isEnabled = false
            next.setTextColor(Color.parseColor("#777474"))
        }
    }
    class SignUpTextWatcher internal constructor() :
        TextWatcher {
        private lateinit var fName: EditText
        private lateinit var mName: EditText
        private lateinit var lName: EditText
        private lateinit var pass: EditText
        private lateinit var cpass: EditText
        private lateinit var male: RadioButton
        private lateinit var female: RadioButton
        private lateinit var next: Button

        constructor(f: EditText,m: EditText,l: EditText,p: EditText,cp: EditText,ma: RadioButton, fe: RadioButton, n : Button) : this() {
            fName = f
            mName = m
            lName = l
            pass = p
            cpass = cp
            male = ma
            female = fe
            next = n
        }
        override fun afterTextChanged(editable: Editable) {
            val condition = fName.text.toString() != "" && mName.text.toString() != "" &&
                    lName.text.toString() != "" && pass.text.toString() != "" &&
                    cpass.text.toString() != "" && (male.isChecked || female.isChecked)
            if (condition){
                next.isEnabled = true
                next.setTextColor(Color.parseColor("#FFFFFFFF"))
                return
            }
            next.isEnabled = false
            next.setTextColor(Color.parseColor("#777474"))
        }
        override fun beforeTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) { }
        override fun onTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) { }
    }
    fun formFiled(): Boolean {
        return fName.text.toString() != "" && mName.text.toString() != "" &&
                lName.text.toString() != "" && pass.text.toString() != "" &&
                cpass.text.toString() != "" && (male.isChecked || female.isChecked)
    }
}