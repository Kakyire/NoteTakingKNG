package com.kakyiretechnologies.notetakingkng.presentation.ui.notesdetail

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.kakyiretechnologies.notetakingkng.R
import com.kakyiretechnologies.notetakingkng.databinding.FragmentNoteDetailBinding
import com.kakyiretechnologies.notetakingkng.domain.model.Note
import com.kakyiretechnologies.notetakingkng.presentation.utils.extensions.navigateToNextPage
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Kakyire
 * Created by Daniel Frimpong on June 08, 2022.
 * https://github.com/kakyire
 */
@AndroidEntryPoint
class NoteDetailFragment : Fragment(R.layout.fragment_note_detail) {


    private lateinit var binding: FragmentNoteDetailBinding

    private val navArgs by navArgs<NoteDetailFragmentArgs>()

    private val viewModel by viewModels<DetailsNoteViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNoteDetailBinding.bind(view)

        setHasOptionsMenu(true)
        observeViewModel()

        viewModel.getNoteDetails(navArgs.noteId)
    }

    private fun observeViewModel() {
        viewModel.noteDetail.observe(viewLifecycleOwner) {
            assignValuesToViews(it)
        }
    }


    private fun assignValuesToViews(note: Note) = with(binding) {
        note.apply {
            tvTitle.text = title
            tvContent.text = content
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detail_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menEdit -> navigateToNextPage(
                NoteDetailFragmentDirections
                    .actionNotesDetailFragmentToAddNotesFragment(navArgs.noteId)
            )
        }
        return super.onOptionsItemSelected(item)
    }
}