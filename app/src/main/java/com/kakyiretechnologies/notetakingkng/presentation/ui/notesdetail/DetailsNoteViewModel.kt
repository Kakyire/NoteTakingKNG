package com.kakyiretechnologies.notetakingkng.presentation.ui.notesdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kakyiretechnologies.notetakingkng.domain.model.Note
import com.kakyiretechnologies.notetakingkng.domain.usecases.GetNoteDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @author Kakyire
 * Created by Daniel Frimpong on June 11, 2022.
 * https://github.com/kakyire
 */


@HiltViewModel
class DetailsNoteViewModel @Inject constructor(private val getNoteDetailsUseCase: GetNoteDetailsUseCase) :
    ViewModel() {


    private val _noteDetail = MutableLiveData<Note>()
    val noteDetail: LiveData<Note> = _noteDetail

    fun getNoteDetails(id: String) {
        _noteDetail.value = getNoteDetailsUseCase.invoke(id)
    }
}