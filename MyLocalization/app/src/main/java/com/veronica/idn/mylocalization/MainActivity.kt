package com.veronica.idn.mylocalization

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import com.veronica.idn.mylocalization.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pokeCount = 3
        val hello =
            resources.getString(R.string.hello_world, "Veronica Putri ", pokeCount, "Anggraini")
        binding.tvHello.text = hello

        val songCount = 5
        val pluralText =
            resources.getQuantityString(R.plurals.numberOfSongsAvailable, songCount, songCount)
        binding.tvPlural.text = pluralText

        binding.tvXliff.text = resources.getString(R.string.app_homeurl)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_change_langauge) {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        }
        return super.onOptionsItemSelected(item)
    }
}