package com.veronica.idn.mypreloaddata.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.veronica.idn.mypreloaddata.R
import com.veronica.idn.mypreloaddata.databinding.ItemMahasiswaRowBinding
import com.veronica.idn.mypreloaddata.entity.MahasiswaModel
import java.util.*
import kotlin.collections.ArrayList

class MahasiswaAdapter : RecyclerView.Adapter<MahasiswaAdapter.MahasiswaViewHolder>() {
    private val listMahasiswa = ArrayList<MahasiswaModel>()

    fun setData(listMahasiswa: ArrayList<MahasiswaModel>) {
        if (listMahasiswa.size > 0) {
            this.listMahasiswa.clear()
        }

        this.listMahasiswa.addAll(listMahasiswa)
        notifyDataSetChanged()
    }

    class MahasiswaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemMahasiswaRowBinding.bind(itemView)
        fun bind(mahasiswa: MahasiswaModel) {
            binding.tvNim.text = mahasiswa.nim
            binding.tvName.text = mahasiswa.name

            val random = Random()
            val color =
                Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
            binding.ivStar.setColorFilter(color)
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MahasiswaViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_mahasiswa_row, parent, false)
        return MahasiswaViewHolder(view)
    }

    override fun onBindViewHolder(holder: MahasiswaViewHolder, position: Int) {
        holder.bind(listMahasiswa[position])
    }

    override fun getItemViewType(position: Int): Int = position

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemCount(): Int = listMahasiswa.size
}