package com.kakyiretechnologies.notetakingkng.presentation.ui.notes

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.kakyiretechnologies.notetakingkng.R
import com.kakyiretechnologies.notetakingkng.databinding.FragmentNotesBinding
import com.kakyiretechnologies.notetakingkng.domain.model.NoteHeaders
import com.kakyiretechnologies.notetakingkng.domain.model.Notes
import com.kakyiretechnologies.notetakingkng.presentation.ui.adapters.DateHeaderAdapter
import com.kakyiretechnologies.notetakingkng.presentation.ui.adapters.NotesAdapter
import com.kakyiretechnologies.notetakingkng.presentation.utils.OnRecyclerViewClickListener
import com.kakyiretechnologies.notetakingkng.presentation.utils.extensions.navigateToNextPage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * @author Kakyire
 * Created by Daniel Frimpong on June 08, 2022.
 * https://github.com/kakyire
 */
private const val TAG = "NotesFragment"

@AndroidEntryPoint
class NotesFragment : Fragment(R.layout.fragment_notes), OnRecyclerViewClickListener {

    private lateinit var binding: FragmentNotesBinding


    @Inject
    lateinit var dateHeaderAdapter: DateHeaderAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNotesBinding.bind(view)

        setupRecyclerView()
        onClickListeners()
    }

    private fun setupRecyclerView() = with(binding) {
        val listOfNotes = mutableListOf<Notes>()

        for (i in 1..6) {
            val createdOn = when {
                i % 2 == 0 -> "June 2, 2022"
                i % 3 == 0 -> "June 3, 2022"
                else -> "June 1, 2022"
            }

            val note = Notes(
                id = "$i",
                title = "title$i",
                content = "content$i",
                modifiedOn = "June 7, 2022",
                createdOn = createdOn
            )

            listOfNotes.add(note)


        }
        if (listOfNotes.isNotEmpty()) rvNotes.visibility = View.VISIBLE
        else tvEmptyNotes.visibility = View.VISIBLE

        val groupedNotes = mutableListOf<NoteHeaders>()
        val notesList = mutableListOf<Notes>()
        listOfNotes.groupBy {
            it.createdOn
        }.forEach { (dates, notes) ->

            groupedNotes.add(NoteHeaders(dates, notes))


        }

        dateHeaderAdapter.submitList(groupedNotes)

        rvNotes.adapter = dateHeaderAdapter

    }

    private fun onClickListeners() = with(binding) {
        btnAdd.setOnClickListener {
            navigateToNextPage(
                NotesFragmentDirections.actionNotesFragmentToAddNotesFragment()
            )
        }
    }

    override fun onItemClick(note: Notes) {
        navigateToNextPage(
            NotesFragmentDirections.actionNotesFragmentToNotesDetailFragment(note)
        )
    }
}