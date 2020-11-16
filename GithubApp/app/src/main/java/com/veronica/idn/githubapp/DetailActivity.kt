package com.veronica.idn.githubapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    companion object{
        const val USER_DATA = "user_data"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.hide()

        iv_arrowback.setOnClickListener {
            val intentMain = Intent(this, MainActivity::class.java)
            startActivity(intentMain)
        }

        val dataUser = intent.getParcelableExtra<User>(USER_DATA) as User
        val dataName = dataUser.name.toString()
        tv_name_detail.text = dataName

        val dataUsername = dataUser.username.toString()
        tv_username.text = dataUsername

        val dataPhotoDetail = dataUser.photo
        iv_photo_detail.setImageResource(dataPhotoDetail)

        val dataFollower = dataUser.follower.toString()
        tv_number_follower.text = dataFollower

        val dataFollowing = dataUser.following.toString()
        tv_number_following.text = dataFollowing

        val dataRepository = dataUser.repository.toString()
        tv_number_repository.text = dataRepository

        val dataLocation = dataUser.location.toString()
        tv_location_detail.text = dataLocation

        val dataCompany = dataUser.company.toString()
        tv_company_detail.text = dataCompany

    }
}