package com.veronica.idn.githubapp

import android.content.Intent
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var dataName: Array<String>
    private lateinit var dataLocation: Array<String>
    private lateinit var dataCompany: Array<String>
    private lateinit var dataFollower: Array<String>
    private lateinit var dataFollowing: Array<String>
    private lateinit var dataRepository: Array<String>
    private lateinit var dataPhoto: TypedArray
    private lateinit var datauserName: Array<String>
    private var users = arrayListOf<User>()
    private lateinit var adapter: UserAdapter

    companion object {
        const val NAME = "name"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        val nameReceiver = intent.getStringExtra(NAME)
        tv_name.text = nameReceiver
        adapter = UserAdapter(this)
        lv_main.adapter = adapter
        prepareData()
        addItem()
        lv_main.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val dataSend = User(
                name = users[position].name,
                username = users[position].username,
                location = users[position].location,
                company = users[position].company,
                follower = users[position].follower,
                following = users[position].following,
                repository = users[position].repository,
                photo = users[position].photo
            )
            val intentToDetail = Intent(this, DetailActivity::class.java)
            intentToDetail.putExtra(DetailActivity.USER_DATA, dataSend)
            startActivity(intentToDetail)
        }

    }

    private fun prepareData() {
        dataName = resources.getStringArray(R.array.name)
        dataLocation = resources.getStringArray(R.array.location)
        dataCompany = resources.getStringArray(R.array.company)
        dataFollower = resources.getStringArray(R.array.followers)
        dataFollowing = resources.getStringArray(R.array.following)
        dataRepository = resources.getStringArray(R.array.repository)
        dataPhoto = resources.obtainTypedArray(R.array.avatar)
        datauserName = resources.getStringArray(R.array.username)

    }

    private fun addItem() {
        for (position in dataName.indices) {
            val user = User(
                dataPhoto.getResourceId(position, -1),
                dataName[position],
                datauserName[position],
                dataLocation[position],
                dataCompany[position],
                dataFollower[position],
                dataFollowing[position],
                dataRepository[position],
            )
            users.add(user)
        }
        adapter.users = users
    }

}