package com.veronica.idn.myservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStartService = findViewById<Button>(R.id.btn_start_service)
        btnStartService.setOnClickListener {
            val mStartServiceIntent = Intent(this, MyService::class.java)
            startService(mStartServiceIntent)
        }

        val btnStartJobIntentService = findViewById<Button>(R.id.btn_start_intent_service)
        btnStartJobIntentService.setOnClickListener {
            val mStartIntentService = Intent(this, MyIntentService::class.java)
            mStartIntentService.putExtra(MyIntentService.EXTRA_DURATION, 5000L)
            MyIntentService.enqueueWork(this, mStartIntentService)

        }
        val btnStartBoundService = findViewById<Button>(R.id.btn_start_bound_service)
        btnStartBoundService.setOnClickListener {

        }
        val btnStopBoundService = findViewById<Button>(R.id.btn_stop_bound_service)
        btnStopBoundService.setOnClickListener { }
    }
}