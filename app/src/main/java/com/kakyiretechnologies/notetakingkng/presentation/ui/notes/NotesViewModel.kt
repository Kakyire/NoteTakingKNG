package com.kakyiretechnologies.notetakingkng.presentation.ui.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kakyiretechnologies.notetakingkng.domain.model.NoteHeaders
import com.kakyiretechnologies.notetakingkng.domain.usecases.GetNotesUseCase
import com.kakyiretechnologies.notetakingkng.domain.usecases.SearchNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Kakyire
 * Created by Daniel Frimpong on June 11, 2022.
 * https://github.com/kakyire
 */

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase,
    private val searchNotesUseCase: SearchNotesUseCase
) : ViewModel() {


    private val _notes = MutableLiveData<List<NoteHeaders>>()
    val notes: LiveData<List<NoteHeaders>> = _notes

    fun getNotes() {
        viewModelScope.launch {
            getNotesUseCase.invoke().collect { _notes.postValue(it) }
        }
    }

    fun searchNotes(query: String) {
        viewModelScope.launch {
            searchNotesUseCase.invoke(query).collectLatest { _notes.postValue(it) }
        }
    }

}