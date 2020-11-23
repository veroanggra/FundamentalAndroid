package com.veronica.idn.myviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setMyButtonEnable()
        my_edittext.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                setMyButtonEnable()
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
        my_button.setOnClickListener {
            Toast.makeText(this, my_edittext.text, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setMyButtonEnable() {
        val result = my_edittext.text
        my_button.isEnabled = result != null && result.toString().isNotEmpty()
    }
}