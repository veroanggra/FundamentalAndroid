 package com.veronica.idn.myunittesting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

 class MainActivity : AppCompatActivity(), View.OnClickListener{
    lateinit var cuboidViewModel: CuboidViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cuboidViewModel = CuboidViewModel(CuboidModel())
        btn_save.setOnClickListener(this)
        btn_circumference.setOnClickListener(this)
        btn_surface_area.setOnClickListener(this)
        btn_volume.setOnClickListener(this)
    }

     override fun onClick(p0: View) {
         val length = et_length.text.toString().trim()
         val width = et_width.text.toString().trim()
         val heigth = et_heigth.text.toString().trim()

         when {
             length.isEmpty() -> et_length.error = getString(R.string.text_error)
             width.isEmpty() -> et_width.error = getString(R.string.text_error)
             heigth.isEmpty() -> et_heigth.error = getString(R.string.text_error)
             else -> {
                 val l = length.toDouble()
                 val w = width.toDouble()
                 val h = heigth.toDouble()

                 when {
                     p0.id == R.id.btn_save ->{
                         cuboidViewModel.save(l, w, h)
                         visible()
                     }
                     p0.id == R.id.btn_circumference ->{
                         tv_result.text = cuboidViewModel.getCircumference().toString()
                         gone()
                     }
                     p0.id == R.id.btn_volume -> {
                         tv_result.text = cuboidViewModel.getVolume().toString()
                         gone()
                     }
                     p0.id == R.id.btn_surface_area -> {
                         tv_result.text = cuboidViewModel.getSurfaceArea().toString()
                         gone()
                     }
                 }
             }
         }
     }

     private fun gone() {
         btn_volume.visibility = View.GONE
         btn_circumference.visibility = View.GONE
         btn_surface_area.visibility = View.GONE
         btn_save.visibility = View.VISIBLE
     }

     private fun visible() {
         btn_volume.visibility = View.VISIBLE
         btn_surface_area.visibility = View.VISIBLE
         btn_circumference.visibility = View.VISIBLE
         btn_save.visibility = View.GONE
     }
 }