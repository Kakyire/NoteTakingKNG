package com.kakyiretechnologies.notetakingkng.presentation.ui.addnotes

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.kakyiretechnologies.notetakingkng.R
import com.kakyiretechnologies.notetakingkng.databinding.FragmentAddEditNoteBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author Kakyire
 * Created by Daniel Frimpong on June 08, 2022.
 * https://github.com/kakyire
 */
@AndroidEntryPoint
class AddEditNoteFragment : Fragment(R.layout.fragment_add_edit_note) {

    private lateinit var binding: FragmentAddEditNoteBinding

    private val navArgs by  navArgs<AddEditNoteFragmentArgs>()

    private val viewModel by viewModels<AddEditNoteViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddEditNoteBinding.bind(view)

    }


    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }
}