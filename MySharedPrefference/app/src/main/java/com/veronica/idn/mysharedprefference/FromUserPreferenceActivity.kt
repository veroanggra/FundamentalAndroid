package com.veronica.idn.mysharedprefference

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.veronica.idn.mysharedprefference.databinding.ActivityFromUserPreferenceBinding

class FromUserPreferenceActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val EXTRA_TYPE_FORM = "extra_type_form"
        const val EXTRA_RESULT = "extra_result"
        const val RESULT_CODE = 101

        const val TYPE_ADD = 1
        const val TYPE_EDIT = 2
        private const val FIELD_REQUIRED = "Field tidak boleh kosong"
        private const val FIELD_DIGIT_ONLY = "Hanyaboleh terisi numerik"
        private const val FIELD_IS_NOT_VALID = "Email tidak valid"
    }

    private lateinit var userModel: User
    private lateinit var binding: ActivityFromUserPreferenceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFromUserPreferenceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener(this)
        userModel = intent.getParcelableExtra<User>("USER") as User
        val formType = intent.getIntExtra(EXTRA_TYPE_FORM, 0)

        var actionBarTitle = ""
        var btnTitle = ""

        when (formType) {
            TYPE_ADD -> {
                actionBarTitle = " Tambah Baru"
                btnTitle = "Simpan"
            }
            TYPE_EDIT -> {
                actionBarTitle = "Ubah"
                btnTitle = "Update"
                showPreferenceinForm()
            }
        }
        supportActionBar?.title = actionBarTitle
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.btnSave.text = btnTitle

    }

    private fun showPreferenceinForm() {
        binding.etName.setText(userModel.name)
        binding.etEmail.setText(userModel.email)
        binding.etAge.setText(userModel.age.toString())
        binding.etPhoneNumber.setText(userModel.phoneNumber)
        if (userModel.isLove) {
            binding.rbYes.isChecked = true
        } else {
            binding.rbNo.isChecked = true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_save) {
            val name = binding.etName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val age = binding.etAge.text.toString().trim()
            val phoneNumber = binding.etPhoneNumber.text.toString().trim()
            val isLoveMu = binding.rgLoveMu.checkedRadioButtonId == R.id.rb_yes

            if (name.isEmpty()) {
                binding.etName.error = FIELD_REQUIRED
                return
            }
            if (email.isEmpty()) {
                binding.etEmail.error = FIELD_REQUIRED
                return
            }
            if (!isValidEmail(email)) {
                binding.etEmail.error = FIELD_IS_NOT_VALID
                return
            }
            if (age.isEmpty()) {
                binding.etAge.error = FIELD_REQUIRED
                return
            }
            if (phoneNumber.isEmpty()) {
                binding.etPhoneNumber.error = FIELD_REQUIRED
                return
            }
            if (!TextUtils.isDigitsOnly(phoneNumber)) {
                binding.etPhoneNumber.error = FIELD_DIGIT_ONLY
                return
            }
            saveUser(name, email, age, phoneNumber, isLoveMu)

            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_RESULT, userModel)
            setResult(RESULT_CODE, resultIntent)
            finish()
        }
    }

    private fun saveUser(
        name: String,
        email: String,
        age: String,
        phoneNumber: String,
        loveMu: Boolean
    ) {
        val userPreference = UserPreference(this)
        userModel.name = name
        userModel.email = email
        userModel.age = Integer.parseInt(age)
        userModel.phoneNumber = phoneNumber
        userModel.isLove = loveMu

        userPreference.setUser(userModel)
        Toast.makeText(this, "Data Tersimpan", Toast.LENGTH_SHORT).show()

    }

    private fun isValidEmail(email: CharSequence): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}