package com.kakyiretechnologies.notetakingkng.presentation.ui.notesdetail

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.kakyiretechnologies.notetakingkng.R
import com.kakyiretechnologies.notetakingkng.databinding.FragmentNotesDetailBinding
import com.kakyiretechnologies.notetakingkng.presentation.utils.extensions.navigateToNextPage
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Kakyire
 * Created by Daniel Frimpong on June 08, 2022.
 * https://github.com/kakyire
 */
@AndroidEntryPoint
class NotesDetailFragment : Fragment(R.layout.fragment_notes_detail) {


    private lateinit var binding: FragmentNotesDetailBinding

    private val navArgs by navArgs<NotesDetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNotesDetailBinding.bind(view)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.edit_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menEdit -> navigateToNextPage(
                NotesDetailFragmentDirections
                    .actionNotesDetailFragmentToAddNotesFragment(navArgs.notes)
            )
        }
        return super.onOptionsItemSelected(item)
    }
}