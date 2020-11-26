package com.veronica.idn.myrecyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.veronica.idn.myrecyclerview.Hero
import com.veronica.idn.myrecyclerview.R
import kotlinx.android.synthetic.main.item_hero_grid.view.*

class GridAdapter(private val listHero: ArrayList<Hero>) :
    RecyclerView.Adapter<GridAdapter.GridViewHolder>() {
    inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(hero: Hero) {
            with(itemView) {
                Glide.with(itemView.context).load(hero.photo)
                    .apply(RequestOptions().override(350, 550))
                    .into(iv_profile_grid)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridAdapter.GridViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_hero_grid, parent, false)
        return GridViewHolder(view)
    }

    override fun onBindViewHolder(holder: GridAdapter.GridViewHolder, position: Int) {
        holder.bind(listHero[position])
    }

    override fun getItemCount(): Int = listHero.size
}