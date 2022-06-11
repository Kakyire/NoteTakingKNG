package com.kakyiretechnologies.notetakingkng.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kakyiretechnologies.notetakingkng.R
import com.kakyiretechnologies.notetakingkng.databinding.NoteListItemsBinding
import com.kakyiretechnologies.notetakingkng.domain.model.Note
import com.kakyiretechnologies.notetakingkng.presentation.utils.OnRecyclerViewClickListener
import javax.inject.Inject

/**
 * @author Kakyire
 * Created by Daniel Frimpong on June 10, 2022.
 * https://github.com/kakyire
 */

class NotesAdapter @Inject constructor(var listener: OnRecyclerViewClickListener) :
    ListAdapter<Note, NotesAdapter.NotesViewHolder>(DIFF_UTIL) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_list_items, parent, false)
        return NotesViewHolder(view)
    }


    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(getItem(position) , listener)
    }



    inner class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = NoteListItemsBinding.bind(itemView)

        fun bind(note: Note, listener: OnRecyclerViewClickListener) = with(binding) {
            tvTitle.text = note.title
            tvContent.text = note.content
            tvCreatedOn.text = note.createdOn
            tvModified.text = note.modifiedOn

            itemView.setOnClickListener {
                listener.onItemClick(note)
            }
        }
    }



    companion object {

        private val DIFF_UTIL = object : DiffUtil.ItemCallback<Note>() {
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem == newItem
            }
        }
    }
}