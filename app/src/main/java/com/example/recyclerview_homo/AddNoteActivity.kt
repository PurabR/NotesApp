package com.example.recyclerview_homo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclerview_homo.databinding.ActivityAddNoteBinding
import com.example.recyclerview_homo.databinding.ItemNoteBinding

class AddNoteActivity: AppCompatActivity(){

    private lateinit var binding: ActivityAddNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener{

            finish()
        }

        binding.btnSave.setOnClickListener {
            addNote()
        }
    }

    private fun addNote() {
        val title = binding.inputTitle.editText?.text?.trim()?.toString()
        val body = binding.inputBody.editText?.text?.trim()?.toString()

        if (title.isNullOrEmpty() || body.isNullOrEmpty()) {
            Toast.makeText(this, "Please Enter Something", Toast.LENGTH_SHORT).show()
            return
        }
        val intent = Intent()
        intent.putExtra("note", Note(title,body))
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}