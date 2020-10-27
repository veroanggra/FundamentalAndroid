package com.veronica.idn.intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.veronica.idn.intent.model.Profile
import kotlinx.android.synthetic.main.activity_move_with_object.*

class MoveWithObjectActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_PROFILE = "extra_profile"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_move_with_object)

        val textObjectResult: TextView = tv_receiver_object
        val profile = intent.getParcelableExtra<Profile>(EXTRA_PROFILE) as Profile
        val result = "Name : ${profile.name},\nAge : ${profile.age},\nCity: ${profile.city}," +
                "\nEmail : ${profile.email}"
        textObjectResult.text = result
    }
}