package com.veronica.idn.barvolume

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    //inisialisasi variable
    private lateinit var etLength: EditText
    private lateinit var etWidth: EditText
    private lateinit var etHeight: EditText
    private lateinit var tvResult: TextView
    private lateinit var btnCalculate: Button

    companion object{
        private const val STATE_RESULT = "state_result"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etLength = findViewById(R.id.et_length)
        etWidth = findViewById(R.id.et_width)
        etHeight = findViewById(R.id.et_height)
        tvResult = findViewById(R.id.tv_result)
        btnCalculate = findViewById(R.id.btn_calculate)

        btnCalculate.setOnClickListener(this)

        if (savedInstanceState != null){
            val result = savedInstanceState.getString(STATE_RESULT)
            tvResult.text = result
        }

    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, tvResult.text.toString())

    }

    override fun onClick(p0: View) {
        if (p0.id == R.id.btn_calculate) {
            val inputLength = etLength.text.toString().trim()
            val inputWidth = etWidth.text.toString().trim()
            val inputHeight = etHeight.text.toString().trim()

            var isEmptyField = false
            when{
                inputLength.isEmpty() -> {
                    isEmptyField = true
                    etLength.error = getString(R.string.text_error)
                }
                inputWidth.isEmpty() -> {
                    isEmptyField = true
                    etWidth.error = getString(R.string.text_error)
                }
                inputHeight.isEmpty() -> {
                    isEmptyField = true
                    etHeight.error = getString(R.string.text_error)
                }
            }
            if (!isEmptyField){
                val volume = inputLength.toDouble() * inputWidth.toDouble() * inputHeight.toDouble()
                tvResult.text = volume.toString()
            }

        }

    }
}