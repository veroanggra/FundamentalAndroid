package com.veronica.idn.mydeepnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.veronica.idn.mydeepnavigation.databinding.ActivityDetailBinding
import com.veronica.idn.mydeepnavigation.databinding.ActivityMainBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailBinding
    companion object{
        const val EXTRA_TITLE = "extra_title"
        const val EXTRA_MESSAGE ="extra_message"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val title = intent.getStringExtra(EXTRA_TITLE)
        val message = intent.getStringExtra(EXTRA_MESSAGE)
        binding.tvTitle.text = title
        binding.tvMessage.text = message
    }
}