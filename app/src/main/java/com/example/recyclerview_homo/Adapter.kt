package com.example.recyclerview_homo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview_homo.databinding.ItemNoteBinding

class Adapter(
    private  val listener: AdapterListener
): RecyclerView.Adapter<StudentViewHolder>() {
    private val  notes = mutableListOf<Note>()

    fun addNote(note: Note) {
        notes.add(note)
        notifyItemInserted(notes.size-1)
    }

    fun addNotes(data: List<Note>) {
        var endIndex = data.size -1
        if (endIndex < 0){
            endIndex = 0
        }
        notes.addAll(data)
        notifyItemRangeInserted(endIndex, notes.size -1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemNoteBinding.inflate(layoutInflater, parent, false)
        return StudentViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
       holder.binding.tvTitle.text = notes[position].title
        holder.binding.tvBody.text = notes[position].body

        holder.binding.btnDlt.setOnClickListener {
            listener.onItemDelete(position)
        }
    }

    fun deleteNote(pos: Int) {
        notes.removeAt(pos)
        notifyItemRemoved(pos)
    }
}

class StudentViewHolder(
   val binding: ItemNoteBinding
): RecyclerView.ViewHolder(binding.root){




}