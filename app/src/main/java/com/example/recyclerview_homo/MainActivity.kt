package com.example.recyclerview_homo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.recyclerview_homo.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    companion object{

        private const val ADD_NOTE_RESULT = 2469
    }


    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: Adapter
    private lateinit var noteSaver: NoteSaver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        noteSaver = NoteSaver(this)


        adapter = Adapter(object : AdapterListener {
            override fun onItemDelete(pos: Int) {
                deleteNote(pos)
            }
        }
        )
        binding.recyclerView.adapter = adapter


        binding.btnAdd.setOnClickListener{
            navigateToAddScreen()
        }
        val savedNotes = noteSaver.getAllNotes()
        if(savedNotes.isEmpty()) {
            binding.animationView.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE
        } else {
            binding.animationView.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
            adapter.addNotes(savedNotes)
        }


    }

    private fun deleteNote(pos: Int) {
        noteSaver.deleteNote(pos)
        adapter.deleteNote(pos)

        if (adapter.itemCount == 0) {
            binding.animationView.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE
        }
    }

    private fun navigateToAddScreen() {
        startActivityForResult(
            Intent(this, AddNoteActivity::class.java),
            ADD_NOTE_RESULT)
    }

    private fun addNote(note: Note) {
        adapter.addNote(note)
        noteSaver.saveNote(note)
        if (adapter.itemCount > 0) {
            binding.animationView.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
        }

    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_NOTE_RESULT && resultCode == Activity.RESULT_OK) {
            val note = data?.getParcelableExtra<Note>("note") ?: return
            addNote(note)

        }


    }
}

