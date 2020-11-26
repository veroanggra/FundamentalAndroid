package com.veronica.idn.myrecyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.veronica.idn.myrecyclerview.Hero
import com.veronica.idn.myrecyclerview.R
import kotlinx.android.synthetic.main.item_cardview.view.*

class CardViewAdapter(private val listHero: ArrayList<Hero>) :
    RecyclerView.Adapter<CardViewAdapter.CardViewHolder>() {
    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(hero: Hero) {
            with(itemView)
            {
                Glide.with(itemView.context).load(hero.photo)
                    .apply(RequestOptions().override(350, 550)).into(iv_profile_card)
                tv_name_card.text = hero.name
                tv_desc_card.text = hero.description
                btn_favorite.setOnClickListener {
                    Toast.makeText(
                        itemView.context,
                        "Favorite ${hero.name}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                btn_share.setOnClickListener {
                    Toast.makeText(
                        itemView.context,
                        "Share ${hero.name}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                itemView.setOnClickListener {
                    Toast.makeText(
                        itemView.context,
                        "Kamu memilih ${hero.name}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CardViewAdapter.CardViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_cardview, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewAdapter.CardViewHolder, position: Int) {
        holder.bind(listHero[position])
    }

    override fun getItemCount(): Int = listHero.size
}