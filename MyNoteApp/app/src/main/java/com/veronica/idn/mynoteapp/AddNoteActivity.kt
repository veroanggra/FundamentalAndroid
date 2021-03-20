package com.veronica.idn.mynoteapp

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.veronica.idn.mynoteapp.databinding.ActivityAddNoteBinding
import com.veronica.idn.mynoteapp.db.DatabaseContract
import com.veronica.idn.mynoteapp.db.DatabaseContract.NoteColumns.Companion.DATE
import com.veronica.idn.mynoteapp.db.NoteHelper
import java.text.SimpleDateFormat
import java.util.*

class AddNoteActivity : AppCompatActivity(), View.OnClickListener {
    private var isEdit = false
    private var note: Note? = null
    private var position: Int = 0
    private lateinit var noteHelper: NoteHelper
    private lateinit var binding: ActivityAddNoteBinding

    companion object {
        const val EXTRA_NOTE = "extra_note"
        const val EXTRA_POSITION = "extra_position"
        const val REQUEST_ADD = 100
        const val RESULT_ADD = 101
        const val REQUEST_UPDATE = 200
        const val RESULT_UPDATE = 201
        const val RESULT_DELETE = 301
        const val ALERT_DIALOG_CLOSE = 10
        const val ALERT_DIALOG_DELETE = 20
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        noteHelper = NoteHelper.getInstance(applicationContext)
        noteHelper.open()

        note = intent.getParcelableExtra(EXTRA_NOTE)
        if (note != null) {
            position = intent.getIntExtra(EXTRA_POSITION, 0)
            isEdit = true
        } else {
            note = Note()
        }
        val actionBarTitle: String
        val btnTitle: String

        if (isEdit) {
            actionBarTitle = "Ubah"
            btnTitle = "Update"

            note?.let {
                binding.etTitle.setText(it.title)
                binding.etDescription.setText(it.description)
            }
        } else {
            actionBarTitle = "Tambah"
            btnTitle = "Simpan"
        }
        supportActionBar?.title = actionBarTitle
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.btnSubmit.text = btnTitle
        binding.btnSubmit.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_submit) {
            val title = binding.etTitle.text.toString().trim()
            val description = binding.etDescription.text.toString().trim()
            if (title.isEmpty()) {
                binding.etTitle.error = "Field can not be blank"
                return
            }

            note?.title = title
            note?.description = description
            val intent = Intent()
            intent.putExtra(EXTRA_NOTE, note)
            intent.putExtra(EXTRA_POSITION, position)
            val values = ContentValues()
            values.put(DatabaseContract.NoteColumns.TITLE, title)
            values.put(DatabaseContract.NoteColumns.DESCRIPTION, description)

            if (isEdit) {
                val result = noteHelper.update(note?.id.toString(), values)
                if (result > 0) {
                    setResult(RESULT_UPDATE, intent)
                    finish()
                } else {
                    Toast.makeText(
                        this@AddNoteActivity,
                        "Gagal mengupdate data",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                note?.date = getCurrentDate()
                values.put(DATE, getCurrentDate())
                val result = noteHelper.insert(values)
                if (result > 0) {
                    note?.id = result.toInt()
                    setResult(RESULT_ADD, intent)
                    finish()
                } else {
                    Toast.makeText(this@AddNoteActivity, "Gagal menambah data", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
        val date = Date()
        return dateFormat.format(date)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (isEdit) {
            menuInflater.inflate(R.menu.menu_form, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_delete -> showAlertDialog(ALERT_DIALOG_DELETE)
            android.R.id.home -> showAlertDialog(ALERT_DIALOG_CLOSE)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        showAlertDialog(ALERT_DIALOG_CLOSE)
    }

    private fun showAlertDialog(type: Int) {
        val isDialogclose = type == ALERT_DIALOG_CLOSE
        val dialogTitle: String
        val dialogMessage: String

        if (isDialogclose) {
            dialogTitle = "Batal"
            dialogMessage = "Apakah anda ingin membatalkan perubakan pada form ?"
        } else {
            dialogMessage = "Apakah anda yakin ingin menghapus item ini?"
            dialogTitle = "Hapus Note"
        }
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle(dialogTitle)
        alertDialogBuilder.setMessage(dialogMessage).setCancelable(false)
            .setPositiveButton("Ya") { _, _ ->
                if (isDialogclose) {
                    finish()
                } else {
                    val result = noteHelper.deleteById(note?.id.toString()).toLong()
                    if (result > 0) {
                        val intent = Intent()
                        intent.putExtra(EXTRA_POSITION, position)
                        setResult(RESULT_DELETE, intent)
                        finish()
                    } else {
                        Toast.makeText(
                            this@AddNoteActivity,
                            "Gagal menghapus data",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            .setNegativeButton("Tidak") { dialog, _ ->
                dialog.cancel()
            }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()

    }
}