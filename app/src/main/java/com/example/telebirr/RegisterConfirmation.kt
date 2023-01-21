package com.example.telebirr

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
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
       /* input[0].addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (input[0].toString() != "") {
                        input[1].requestFocus()
              }
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }
        )
        input[1].addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (input[1].toString() != "") {
                    input[2].requestFocus()
                } else {
                    input[0].requestFocus()
                }
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }
        )
        input[2].addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (input[2].toString() != "") {
                    input[3].requestFocus()
                } else {
                    input[1].requestFocus()
                }
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }
        )
        input[3].addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (input[3].toString() != "") {
                    input[4].requestFocus()
                } else {
                    input[2].requestFocus()
                }
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }
        )
        input[4].addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (input[4].toString() != "") {
                    input[5].requestFocus()
                } else {
                    input[3].requestFocus()
                }
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }
        )
        input[5].addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (input[5].toString() != "") {
                    input[5].clearFocus()
                } else {
                    input[4].requestFocus()
                }
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }
        )
*/
      /*  input[0].moveToNext(next = input[1], prev = null) {
            input[0].isEnabled = it
        }
        input[1].moveToNext(next = input[2], prev = input[0]) {
            input[1].isEnabled = it
        }
        input[2].moveToNext(next = input[3], prev = input[1]) {
            input[2].isEnabled = it
        }
        input[3].moveToNext(next = input[4], prev = input[2]) {
            input[3].isEnabled = it
        }
        input[4].moveToNext(next = input[5], prev = input[3]) {
            input[4].isEnabled = it
        }
        input[5].moveToNext(next = null, prev = input[4]) {
            input[5].isEnabled = it
        }*/
        //GenericTextWatcher here works only for moving to next EditText when a number is entered
        //first parameter is the current EditText and second parameter is next EditText
        input[0].addTextChangedListener(GenericTextWatcher(input[0], input[1]))
        input[1].addTextChangedListener(GenericTextWatcher(input[1], input[2]))
        input[2].addTextChangedListener(GenericTextWatcher(input[2], input[3]))
        input[3].addTextChangedListener(GenericTextWatcher(input[3], input[4]))
        input[4].addTextChangedListener(GenericTextWatcher(input[4], input[5]))
        input[5].addTextChangedListener(GenericTextWatcher(input[5], null))
        //GenericKeyEvent here works for deleting the element and to switch back to previous EditText
        //first parameter is the current EditText and second parameter is previous EditText
        input[0].setOnKeyListener(GenericKeyEvent(input[0], null))
        input[1].setOnKeyListener(GenericKeyEvent(input[1], input[0]))
        input[2].setOnKeyListener(GenericKeyEvent(input[2], input[1]))
        input[3].setOnKeyListener(GenericKeyEvent(input[3],input[2]))
        input[4].setOnKeyListener(GenericKeyEvent(input[4],input[3]))
        input[5].setOnKeyListener(GenericKeyEvent(input[5],input[4]))
    }
    class GenericKeyEvent internal constructor(private val currentView: EditText, private val previousView: EditText?) : View.OnKeyListener{
        override fun onKey(p0: View?, keyCode: Int, event: KeyEvent?): Boolean {
            if(event!!.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL && currentView.id != R.id.confirmation_code_1 && currentView.text.isEmpty()) {
                //If current is empty then previous EditText's number will also be deleted
                previousView!!.text = null
                previousView.requestFocus()
                return true
            }
            return false
        }
    }

    class GenericTextWatcher internal constructor(private val currentView: View, private val nextView: View?) : TextWatcher {
        override fun afterTextChanged(editable: Editable) { // TODO Auto-generated method stub
            val text = editable.toString()
            when (currentView.id) {
                R.id.confirmation_code_1 -> if (text.length == 1) nextView!!.requestFocus()
                R.id.confirmation_code_2 -> if (text.length == 1) nextView!!.requestFocus()
                R.id.confirmation_code_3 -> if (text.length == 1) nextView!!.requestFocus()
                R.id.confirmation_code_4 -> if (text.length == 1) nextView!!.requestFocus()
                R.id.confirmation_code_5 -> if (text.length == 1) nextView!!.requestFocus()
                R.id.confirmation_code_6 -> if (text.length == 1) nextView!!.requestFocus()
            }
        }
        override fun beforeTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) { }
        override fun onTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) { }

    }
    /*fun EditText.moveToNext(next: EditText?, prev: EditText?, function: (isEnabled:Boolean) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                val number = p0.toString()
                if (next != null) {
                    if (number.length == 1) {
                        next.requestFocus()
                    }
                } else {
                    function(true)
                }
                prev?.let { prev ->
                    if (number.isEmpty()) {
                        prev.requestFocus()
                        function(false)
                    }
                }

            }

        })
    }*/
}
