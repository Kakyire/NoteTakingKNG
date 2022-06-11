package com.kakyiretechnologies.notetakingkng.presentation.ui.addnotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakyiretechnologies.notetakingkng.domain.model.Note
import com.kakyiretechnologies.notetakingkng.domain.usecases.AddNotesUseCase
import com.kakyiretechnologies.notetakingkng.domain.usecases.DeleteNotesUseCase
import com.kakyiretechnologies.notetakingkng.domain.usecases.UpdateNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Kakyire
 * Created by Daniel Frimpong on June 11, 2022.
 * https://github.com/kakyire
 */

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    private val addNotesUseCase: AddNotesUseCase,
    private val updateNotesUseCase: UpdateNotesUseCase,
    private val deleteNotesUseCase: DeleteNotesUseCase
) : ViewModel() {

    fun addNote(note: Note) {
        viewModelScope.launch {
            addNotesUseCase.invoke(note)
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            updateNotesUseCase.invoke(note)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            deleteNotesUseCase.invoke(note)
        }
    }
}