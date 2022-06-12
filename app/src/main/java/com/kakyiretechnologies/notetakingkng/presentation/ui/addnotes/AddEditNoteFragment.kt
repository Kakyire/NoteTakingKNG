package com.kakyiretechnologies.notetakingkng.presentation.ui.addnotes

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.kakyiretechnologies.notetakingkng.R
import com.kakyiretechnologies.notetakingkng.databinding.FragmentAddEditNoteBinding
import com.kakyiretechnologies.notetakingkng.domain.model.Note
import com.kakyiretechnologies.notetakingkng.domain.utils.getCurrentDate
import com.kakyiretechnologies.notetakingkng.presentation.ui.notesdetail.DetailsNoteViewModel
import com.kakyiretechnologies.notetakingkng.presentation.utils.extensions.navigateToNextPage
import com.kakyiretechnologies.notetakingkng.presentation.utils.extensions.observeLiveData
import com.kakyiretechnologies.notetakingkng.presentation.utils.extensions.showMessage
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Kakyire
 * Created by Daniel Frimpong on June 08, 2022.
 * https://github.com/kakyire
 */
@AndroidEntryPoint
class AddEditNoteFragment : Fragment(R.layout.fragment_add_edit_note) {

    private lateinit var binding: FragmentAddEditNoteBinding

    private val navArgs by navArgs<AddEditNoteFragmentArgs>()

    private val addEditNoteViewModel by viewModels<AddEditNoteViewModel>()
    private val detailsNoteViewModel by viewModels<DetailsNoteViewModel>()

    private var note = Note()
    private var noteTitle = ""
    private var noteContent = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddEditNoteBinding.bind(view)

        setHasOptionsMenu(true)
        navArgs.noteId?.let {
            detailsNoteViewModel.getNoteDetails(it)
        }

        observeViewModel()

    }

    private fun observeViewModel() {
        observeLiveData(detailsNoteViewModel.noteDetail) {
            note = it
            preAssignValuesToViews(it)
        }
    }

    private fun preAssignValuesToViews(note: Note) = with(binding) {
        note.apply {
            edtContent.setText(content)
            edtTitle.setText(title)
        }
    }

    private fun getValues() = with(binding) {
        noteTitle = edtTitle.text.toString()
        noteContent = edtContent.text.toString()
    }

    private fun saveNote() {
        getValues()

        if (noteTitle.isEmpty()) {
            showMessage(getString(R.string.note_title_is_required))
            binding.edtTitle.requestFocus()
            return
        }

        note.apply {
            title = noteTitle
            content = noteContent
            modifiedOn = getCurrentDate()
        }

        if (navArgs.noteId != null) {
            addEditNoteViewModel.updateNote(note)
            navigateToNextPage(
                AddEditNoteFragmentDirections
                    .actionAddNotesFragmentToNotesDetailFragment(note.id)
            )
        } else {
            addEditNoteViewModel.addNote(note)
            navigateToNextPage(
                AddEditNoteFragmentDirections
                    .actionAddNotesFragmentToNotesFragment()
            )
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.add_edit_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menSave -> saveNote()

        }
        return super.onOptionsItemSelected(item)
    }
}