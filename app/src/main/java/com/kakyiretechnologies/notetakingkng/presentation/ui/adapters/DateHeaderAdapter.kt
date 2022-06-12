package com.kakyiretechnologies.notetakingkng.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kakyiretechnologies.notetakingkng.R
import com.kakyiretechnologies.notetakingkng.databinding.DateHeaderListItemsBinding
import com.kakyiretechnologies.notetakingkng.domain.model.NoteHeaders
import com.kakyiretechnologies.notetakingkng.presentation.utils.OnRecyclerViewClickListener
import javax.inject.Inject

/**
 * @author Kakyire
 * Created by Daniel Frimpong on June 10, 2022.
 * https://github.com/kakyire
 */

class DateHeaderAdapter @Inject constructor(
    private val listener: OnRecyclerViewClickListener
) :
    ListAdapter<NoteHeaders, DateHeaderAdapter.DateHeaderViewHolder>(DIFF_UTIL) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateHeaderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.date_header_list_items, parent, false)
        return DateHeaderViewHolder(view)
    }

    override fun onBindViewHolder(holder: DateHeaderViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    inner class DateHeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = DateHeaderListItemsBinding.bind(itemView)

        private val notesAdapter = NotesAdapter(listener)
        fun bind(
            noteHeaders: NoteHeaders,
            listener: OnRecyclerViewClickListener
        ) = with(binding) {
            tvDate.text = noteHeaders.date//.daysPast()
            rvNotes.adapter = notesAdapter
            notesAdapter.listener = listener
            notesAdapter.submitList(noteHeaders.notes)
        }
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<NoteHeaders>() {
            override fun areItemsTheSame(oldItem: NoteHeaders, newItem: NoteHeaders): Boolean {
                return oldItem.date == newItem.date
            }

            override fun areContentsTheSame(oldItem: NoteHeaders, newItem: NoteHeaders): Boolean {
                return oldItem == newItem
            }
        }
    }
}