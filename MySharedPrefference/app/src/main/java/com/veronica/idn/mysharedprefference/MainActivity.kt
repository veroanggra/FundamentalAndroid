package com.veronica.idn.mysharedprefference

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.veronica.idn.mysharedprefference.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mUserPreference: UserPreference
    private var isPreferenceEmpty = false
    private lateinit var user: User
    private lateinit var binding: ActivityMainBinding

    companion object {
        private const val REQUEST_CODE = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "My user preference"
        mUserPreference = UserPreference(this)
        showExistingPreference()
        binding.btnSave.setOnClickListener(this)
    }

    private fun showExistingPreference() {
        user = mUserPreference.getUser()
        populateView(user)
        checkForm(user)

    }

    private fun checkForm(user: User) {
        when {
            user.name.toString().isNotEmpty() -> {
                binding.btnSave.text = getText(R.string.change)
                isPreferenceEmpty = false
            }
            else -> {
                binding.btnSave.text = getString(R.string.save)
                isPreferenceEmpty = true
            }
        }

    }

    private fun populateView(user: User) {
        binding.tvName.text = if (user.name.toString().isEmpty()) "Tidak Ada" else user.name
        binding.tvAge.text = if (user.age.toString().isEmpty()) "Tidak Ada" else user.age.toString()
        binding.tvEmail.text = if (user.email.toString().isEmpty()) "Tidak Ada" else user.email
        binding.tvPhone.text =
            if (user.phoneNumber.toString().isEmpty()) " Tidak Ada" else user.phoneNumber
        binding.tvIsLove.text = if (user.isLove) "Ya" else "Tidak"
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_save) {
            val intent = Intent(this@MainActivity, FromUserPreferenceActivity::class.java)
            when {
                isPreferenceEmpty -> {
                    intent.putExtra(
                        FromUserPreferenceActivity.EXTRA_TYPE_FORM,
                        FromUserPreferenceActivity.TYPE_ADD
                    )
                    intent.putExtra("USER", user)
                }
                else -> {
                    intent.putExtra(
                        FromUserPreferenceActivity.EXTRA_TYPE_FORM,
                        FromUserPreferenceActivity.TYPE_EDIT
                    )
                    intent.putExtra("USER", user)
                }
            }
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            if (resultCode == FromUserPreferenceActivity.RESULT_CODE) {
                user =
                    data?.getParcelableExtra<User>(FromUserPreferenceActivity.EXTRA_RESULT) as User
                populateView(user)
                checkForm(user)
            }
        }
    }
}