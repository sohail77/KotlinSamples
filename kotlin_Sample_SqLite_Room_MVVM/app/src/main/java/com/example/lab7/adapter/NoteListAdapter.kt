package com.example.lab7.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab7.R
import com.example.lab7.database.Note
import kotlinx.android.synthetic.main.note_item.view.*


class NoteListAdapter(
    private var context: Context
) : RecyclerView.Adapter<NoteListAdapter.ViewHolder>() {

    private var noteList: List<Note> = listOf()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.note_item, viewGroup, false)
        return ViewHolder(itemView)
    }


    override fun getItemCount() = noteList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.title.text = noteList.get(position).title
        holder.description.text = noteList.get(position).description
        holder.category.text = context.getString(R.string.Category, noteList.get(position).category)
        holder.importance.text = context.getString(R.string.importance, noteList.get(position).importance)

    }

    fun setNotes(notes: List<Note>) {
        this.noteList = notes
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView
            get() = itemView.title_text
        val description: TextView
            get() = itemView.description_text
        val category: TextView
            get() = itemView.category_text
        val importance: TextView
            get() = itemView.priority_text
    }


}