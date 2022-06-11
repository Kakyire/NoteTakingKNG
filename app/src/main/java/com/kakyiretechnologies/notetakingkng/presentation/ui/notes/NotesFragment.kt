package com.kakyiretechnologies.notetakingkng.presentation.ui.notes

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kakyiretechnologies.notetakingkng.R
import com.kakyiretechnologies.notetakingkng.databinding.FragmentNotesBinding
import com.kakyiretechnologies.notetakingkng.domain.model.Note
import com.kakyiretechnologies.notetakingkng.presentation.ui.adapters.DateHeaderAdapter
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

    private val viewModel by viewModels<NotesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNotesBinding.bind(view)

        binding.rvNotes.adapter = dateHeaderAdapter

        observeViewModel()
        onClickListeners()
    }

    private fun observeViewModel() = with(viewModel) {
        getNotes()
        notes.observe(viewLifecycleOwner) {
            dateHeaderAdapter.submitList(it)
        }
    }

    private fun searchForNotes(query: String?) = with(viewModel) {

        if (!query.isNullOrEmpty()) {
            searchNotes(query)
            return@with
        }

        getNotes()

    }

    private fun onClickListeners() = with(binding) {
        btnAdd.setOnClickListener {
            navigateToNextPage(
                NotesFragmentDirections.actionNotesFragmentToAddNotesFragment()
            )
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchForNotes(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchForNotes(newText)
                return true
            }
        })

    }

    override fun onItemClick(note: Note) {
        navigateToNextPage(
            NotesFragmentDirections.actionNotesFragmentToNotesDetailFragment(note)
        )
    }
}