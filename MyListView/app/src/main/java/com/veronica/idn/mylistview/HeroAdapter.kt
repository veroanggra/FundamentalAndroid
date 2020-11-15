package com.veronica.idn.mylistview

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView

class HeroAdapter internal constructor(private val context: Context) : BaseAdapter() {
    internal var heroes = arrayListOf<Hero>()

    override fun getView(p0: Int, p1: View?, p2: ViewGroup): View {
        var itemView = p1
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.item_hero, p2, false)
        }
        val viewHolder = ViewHolder(itemView as View)
        val hero = getItem(p0) as Hero
        viewHolder.bind(hero)
        return itemView
    }

    private inner class ViewHolder internal constructor(view: View) {
        private val textName: TextView = view.findViewById(R.id.tv_name)
        private val textDesc: TextView = view.findViewById(R.id.tv_description)
        private val ivPhoto: CircleImageView = view.findViewById(R.id.iv_photo)

        fun bind(hero: Hero) {
            textName.text = hero.name
            textDesc.text = hero.description
            ivPhoto.setImageResource(hero.photo)
        }

    }


    override fun getItem(p0: Int): Any {
        return heroes[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return heroes.size
    }
}