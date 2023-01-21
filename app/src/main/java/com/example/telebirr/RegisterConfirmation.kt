package com.example.telebirr

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterConfirmation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_confirmation)
        val input = mutableListOf<EditText>(
            findViewById(R.id.confirmation_code_1),
            findViewById(R.id.confirmation_code_2),
            findViewById(R.id.confirmation_code_3),
            findViewById(R.id.confirmation_code_4),
            findViewById(R.id.confirmation_code_5),
            findViewById(R.id.confirmation_code_6)
        )
        val next = findViewById<Button>(R.id.su_c_next_button)
        next.isEnabled = true //TODO FIX THIS
        //GenericTextWatcher here works only for moving to next EditText when a number is entered
        //first parameter is the current EditText and second parameter is next EditText
        input[0].addTextChangedListener(GenericTextWatcher(input[0], input[1],input,next))
        input[1].addTextChangedListener(GenericTextWatcher(input[1], input[2],input,next))
        input[2].addTextChangedListener(GenericTextWatcher(input[2], input[3],input,next))
        input[3].addTextChangedListener(GenericTextWatcher(input[3], input[4],input,next))
        input[4].addTextChangedListener(GenericTextWatcher(input[4], input[5],input,next))
        input[5].addTextChangedListener(GenericTextWatcher(input[5], null,input,next))
        //GenericKeyEvent here works for deleting the element and to switch back to previous EditText
        //first parameter is the current EditText and second parameter is previous EditText
        input[0].setOnKeyListener(GenericKeyEvent(input[0], null,input,next))
        input[1].setOnKeyListener(GenericKeyEvent(input[1], input[0],input,next))
        input[2].setOnKeyListener(GenericKeyEvent(input[2], input[1],input,next))
        input[3].setOnKeyListener(GenericKeyEvent(input[3],input[2],input,next))
        input[4].setOnKeyListener(GenericKeyEvent(input[4],input[3],input,next))
        input[5].setOnKeyListener(GenericKeyEvent(input[5],input[4],input,next))

        next.setOnClickListener {
            startActivity(Intent(this,RegisterInformation::class.java))
        }
    }
    class GenericKeyEvent internal constructor(
        private val currentView: EditText, private val previousView: EditText?,private val i: MutableList<EditText>,private val next: Button
    ) : View.OnKeyListener{
        override fun onKey(p0: View?, keyCode: Int, event: KeyEvent?): Boolean {
            if(event!!.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL && currentView.id != R.id.confirmation_code_1 && currentView.text.isEmpty()) {
                //If current is empty then previous EditText's number will also be deleted
                previousView!!.text = null
                previousView.requestFocus()
                next.isEnabled = false
                next.setTextColor(Color.parseColor("#777474"))
                return true
            }
            return false
        }
    }

    class GenericTextWatcher internal constructor(
        private val currentView: View, private val nextView: View?,private val i: MutableList<EditText>,private val next: Button
        ) : TextWatcher {
        override fun afterTextChanged(editable: Editable) { // TODO Auto-generated method stub
            val text = editable.toString()
            when (currentView.id) {
                R.id.confirmation_code_1 -> if (text.length == 1) nextView!!.requestFocus()
                R.id.confirmation_code_2 -> if (text.length == 1) nextView!!.requestFocus()
                R.id.confirmation_code_3 -> if (text.length == 1) nextView!!.requestFocus()
                R.id.confirmation_code_4 -> if (text.length == 1) nextView!!.requestFocus()
                R.id.confirmation_code_5 -> if (text.length == 1) nextView!!.requestFocus()
            }
            for (x in i){
                if (x.text.length != 1){
                    return
                }
            }
            //TODO CHECK VALIDITY
            next.isEnabled = true
            next.setTextColor(Color.parseColor("#FFFFFFFF"))
            //TODO: Hide keyboard when done

        }
        override fun beforeTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) { }
        override fun onTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) { }

    }
}
