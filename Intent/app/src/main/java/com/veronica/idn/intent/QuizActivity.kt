package com.veronica.idn.intent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {
    companion object{
        const val  EXTRA_SELECTED_OPTION_VALUE = "extra_selected_option_value"
        const val RESULT_CODE = 110
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        btn_choose.setOnClickListener {
            if (rg_option.checkedRadioButtonId != 0){
                var value = 0
                when (rg_option.checkedRadioButtonId){
                    R.id.rb_7 -> value = 7
                    R.id.rb_10 -> value = 10
                    R.id.rb_12 -> value = 12
                    R.id.rb_2 -> value = 2
                }
                var optionResultIntent = Intent()
                optionResultIntent.putExtra(EXTRA_SELECTED_OPTION_VALUE, value)
                setResult(RESULT_CODE, optionResultIntent)
                finish()
            }

        }
    }
}