package com.veronica.idn.intent

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.veronica.idn.intent.model.Profile
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object{
        private const val REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_move_activity.setOnClickListener {
            val moveActivityIntent = Intent(this, MoveActivity::class.java)
            startActivity(moveActivityIntent)
        }

        btn_move_with_data.setOnClickListener {
            val moveWithDataIntent = Intent(this, MoveWithDataActivity::class.java)
            moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_NAME, "Veronica")
            moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_AGE, 23)
            startActivity(moveWithDataIntent)

        }

        btn_move_with_object.setOnClickListener {
            val profile = Profile("Veronica",
                23,
                "Bogor",
                "veronicaputri497@gmail.com")
            val moveWithObjectIntent = Intent(this, MoveWithObjectActivity::class.java)
            moveWithObjectIntent.putExtra(MoveWithObjectActivity.EXTRA_PROFILE, profile)
            startActivity(moveWithObjectIntent)

        }

        btn_dial_number.setOnClickListener {
            val phoneNumber = "081281462016"
            val dialIntent = Intent(Intent.ACTION_DIAL,
                Uri.parse("tel:$phoneNumber"))
            startActivity(dialIntent)
        }

        btn_quiz.setOnClickListener {
            val quizIntent =  Intent(this, QuizActivity::class.java)
            startActivityForResult(quizIntent, REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE){
            if (resultCode == QuizActivity.RESULT_CODE) {
                val selectedValue = data?.getIntExtra(QuizActivity.EXTRA_SELECTED_OPTION_VALUE, 0)
                tv_result_quiz.text = "Hasil : $selectedValue"
            }
        }
    }
}