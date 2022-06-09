package com.kakyiretechnologies.notetakingkng.presentation.ui.notes

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.kakyiretechnologies.notetakingkng.R
import com.kakyiretechnologies.notetakingkng.databinding.FragmentNotesBinding
import com.kakyiretechnologies.notetakingkng.presentation.utils.extensions.navigateToNextPage
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Kakyire
 * Created by Daniel Frimpong on June 08, 2022.
 * https://github.com/kakyire
 */
private const val TAG = "NotesFragment"

@AndroidEntryPoint
class NotesFragment : Fragment(R.layout.fragment_notes) {

    private lateinit var binding: FragmentNotesBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNotesBinding.bind(view)

        onClickListeners()
    }

    private fun onClickListeners() = with(binding) {
        btnAdd.setOnClickListener {
            navigateToNextPage(
                NotesFragmentDirections.actionNotesFragmentToAddNotesFragment()
            )
        }
    }
}