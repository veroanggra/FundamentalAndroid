package com.veronica.idn.mypreloaddata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.veronica.idn.mypreloaddata.adapter.MahasiswaAdapter
import com.veronica.idn.mypreloaddata.databinding.ActivityMahasiswaBinding
import com.veronica.idn.mypreloaddata.db.MahasiswaHelper

class MahasiswaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMahasiswaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMahasiswaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvMahasiswa.layoutManager = LinearLayoutManager(this)
        val mahasiswaAdapter = MahasiswaAdapter()
        binding.rvMahasiswa.adapter = mahasiswaAdapter

        val mahasiswaHelper = MahasiswaHelper(this)
        mahasiswaHelper.open()

        val mahasiswaModels = mahasiswaHelper.getAllData()
        mahasiswaHelper.close()

        mahasiswaAdapter.setData(mahasiswaModels)
    }
}