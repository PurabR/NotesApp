package com.example.recyclerview_homo

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.Exception

class NoteSaver(private val context: Context) {

    companion object {
        private const val NOTES = "notes"
    }

    private val sharedPreferences = context.getSharedPreferences(NOTES, Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()
    private val gson = Gson()

    fun saveNote(note: Note) {
        val allNotes = getAllNotes().toMutableList()
        allNotes.add(note)
        val listType = object : TypeToken<List<Note>>() {}.type
        val data = gson.toJson(allNotes, listType)

        editor.putString(NOTES, data)
        editor.commit()

    }

    fun getAllNotes(): List<Note> {
        return try {
            val data = sharedPreferences.getString(NOTES, " ")

            val listType = object : TypeToken<List<Note>>() {}.type
            return gson.fromJson<List<Note>>(data, listType)
        } catch (e: Exception) {
            return emptyList()
        }
    }

    fun deleteNote(pos: Int) {

        val allNotes = getAllNotes().toMutableList()
        allNotes.removeAt(pos)
        val listType = object : TypeToken<List<Note>>() {}.type
        val data = gson.toJson(allNotes, listType)

        editor.putString(NOTES, data)
        editor.commit()

    }

}