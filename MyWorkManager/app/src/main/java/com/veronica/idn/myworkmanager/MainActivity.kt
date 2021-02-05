package com.veronica.idn.myworkmanager

import android.accessibilityservice.GestureDescription
import android.app.AlertDialog
import android.app.Notification
import android.app.job.JobInfo
import android.media.audiofx.DynamicsProcessing
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.work.*
import com.veronica.idn.myworkmanager.databinding.ActivityMainBinding
import cz.msebera.android.httpclient.client.config.RequestConfig
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var periodicWorkRequest: PeriodicWorkRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnOneTimeTask.setOnClickListener(this)
        binding.btnRunPeriodicTask.setOnClickListener(this)
        binding.btnCancelPeriodicTask.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_one_time_task -> startOneTimeTask()
            R.id.btn_run_periodic_task -> startPeriodicTask()
            R.id.btn_cancel_periodic_task -> cancelPeriodicTask()
        }
    }

    private fun cancelPeriodicTask() {
        WorkManager.getInstance().cancelWorkById(periodicWorkRequest.id)
    }

    private fun startPeriodicTask() {
        binding.tvStatus.text = getText(R.string.status)
        val data = Data.Builder().putString(MyWork.EXTRA_CITY, binding.etCity.text.toString())
            .build()
        val constraints =
            Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        periodicWorkRequest = PeriodicWorkRequest.Builder(MyWork::class.java, 15, TimeUnit.MINUTES)
            .setInputData(data)
            .setConstraints(constraints)
            .build()
        WorkManager.getInstance().enqueue(periodicWorkRequest)
        WorkManager.getInstance()
            .getWorkInfoByIdLiveData(periodicWorkRequest.id).observe(this, { workInfo ->
                val status = workInfo.state.name
                binding.tvStatus.append("\n" + status)
                binding.btnCancelPeriodicTask.isEnabled = false
                if (workInfo.state == WorkInfo.State.ENQUEUED) {
                    binding.btnCancelPeriodicTask.isEnabled = true
                }
            })
    }

    private fun startOneTimeTask() {
        binding.tvStatus.text = getString(R.string.status)
        val data = Data.Builder()
            .putString(MyWork.EXTRA_CITY, binding.etCity.text.toString())
            .build()
        val constraint = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()

        val oneTimeWorkRequest = OneTimeWorkRequest.Builder(MyWork::class.java)
            .setInputData(data)
            .setConstraints(constraint).build()

        WorkManager.getInstance().enqueue(oneTimeWorkRequest)
        WorkManager.getInstance().getWorkInfoByIdLiveData(oneTimeWorkRequest.id)
            .observe(this, { workInfo ->
                val status = workInfo.state.name
                binding.tvStatus.append("\n" + status)
            })
    }
}