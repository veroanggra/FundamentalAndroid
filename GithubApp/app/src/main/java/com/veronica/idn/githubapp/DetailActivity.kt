package com.veronica.idn.githubapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class DetailActivity : AppCompatActivity() {
    companion object{
        const val USER_DATA = "user_data"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.hide()
    }
}