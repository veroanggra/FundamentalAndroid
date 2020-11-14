package com.veronica.idn.githubapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_intro.*

class IntroActivity : AppCompatActivity(), View.OnClickListener{
    companion object {
        const val NAME = "name"

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        supportActionBar?.hide()
        btn_send_name.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        val nameUser: String = et_name_intro.text.toString().trim()
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(NAME, nameUser)
        startActivity(intent)
        
    }
}