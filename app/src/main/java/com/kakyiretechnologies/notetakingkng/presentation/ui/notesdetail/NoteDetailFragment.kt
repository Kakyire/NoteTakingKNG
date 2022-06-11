package com.kakyiretechnologies.notetakingkng.presentation.ui.notesdetail

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.kakyiretechnologies.notetakingkng.R
import com.kakyiretechnologies.notetakingkng.databinding.FragmentNoteDetailBinding
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNoteDetailBinding.bind(view)
        assignValuesToViews()
        setHasOptionsMenu(true)
    }

    private fun assignValuesToViews() = with(binding) {
        navArgs.note.apply {
            tvTitle.text = title
            tvContent.text = content
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.edit_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menEdit -> navigateToNextPage(
                NoteDetailFragmentDirections
                    .actionNotesDetailFragmentToAddNotesFragment(navArgs.note)
            )
        }
        return super.onOptionsItemSelected(item)
    }
}