package com.veronica.idn.myquotes

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.veronica.idn.myquotes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getRandomQuotes()
    }

    private fun getRandomQuotes() {
    }
}