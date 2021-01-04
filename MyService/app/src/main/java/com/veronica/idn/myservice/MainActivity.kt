package com.veronica.idn.myservice

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private var mServiceBound = false
    private lateinit var mBoundService: MyBoundService

    private val mServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder) {
            val myBinder = p1 as MyBoundService.MyBinder
            mBoundService = myBinder.getService
            mServiceBound = true
        }

        override fun onServiceDisconnected(p0: ComponentName) {
            mServiceBound = false
        }

    }

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
            val mBoundServiceIntent = Intent(this, MyBoundService::class.java)
            bindService(mBoundServiceIntent, mServiceConnection, BIND_AUTO_CREATE)

        }
        val btnStopBoundService = findViewById<Button>(R.id.btn_stop_bound_service)
        btnStopBoundService.setOnClickListener {
            unbindService(mServiceConnection)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mServiceBound){
            unbindService(mServiceConnection)
        }
    }
}