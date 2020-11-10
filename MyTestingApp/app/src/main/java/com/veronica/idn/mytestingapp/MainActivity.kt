package com.veronica.idn.mytestingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import java.lang.StringBuilder

class MainActivity : AppCompatActivity(), View.OnClickListener{
    private lateinit var btnSetValue: Button
    private lateinit var tvHello : TextView
    private lateinit var ivPreview : ImageView

    private var names = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvHello = findViewById(R.id.tv_hello)
        btnSetValue = findViewById(R.id.btn_set_value)
        ivPreview = findViewById(R.id.iv_preview)

        btnSetValue.setOnClickListener(this)
        names.add("Veronica")
        names.add("Putri")
        names.add("Anggraini")

//        ivPreview.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.picture))
            Glide.with(this).load(R.drawable.picture).into(ivPreview)

    }

    override fun onClick(p0: View) {
        if (p0.id == R.id.btn_set_value){
            Log.d("MainActivity", names.toString())
            val name = StringBuilder()
            for (i in 0..2){
                name.append(names[i]).append("\n")
            }
            tvHello.text = name.toString()


        }
    }
}