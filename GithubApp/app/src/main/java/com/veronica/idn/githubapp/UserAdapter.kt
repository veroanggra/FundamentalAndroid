package com.veronica.idn.githubapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import de.hdodenhof.circleimageview.CircleImageView

class UserAdapter internal constructor(private val context: Context) : BaseAdapter() {
    internal var users = arrayListOf<User>()

    override fun getCount(): Int {
        return users.size
    }

    override fun getItem(p0: Int): Any {
        return users[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var itemView = p1
        if (itemView == null){
            itemView = LayoutInflater.from(context).inflate(R.layout.item_user, p2, false)
        }
        val viewHolder = ViewHolder(itemView as View)
        val user = getItem(p0) as User
        viewHolder.bind(user)
        return itemView

    }

    private inner class ViewHolder internal constructor(view: View) {
        private val textName: TextView = view.findViewById(R.id.tv_name_list)
        private val textLocation: TextView = view.findViewById(R.id.tv_location_list)
        private val textCompany: TextView = view.findViewById(R.id.tv_company_list)
        private val ivProfile: CircleImageView = view.findViewById(R.id.iv_user)
        fun bind(user: User) {
            textName.text = user.name
            textLocation.text = user.location
            textCompany.text = user.location
            ivProfile.setImageResource(user.photo)

        }
    }
}