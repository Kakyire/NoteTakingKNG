package com.kakyiretechnologies.notetakingkng.domain.usecases

import com.kakyiretechnologies.notetakingkng.domain.model.Note
import com.kakyiretechnologies.notetakingkng.domain.model.NoteHeaders
import com.kakyiretechnologies.notetakingkng.domain.repositories.NotesRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @author Kakyire
 * Created by Daniel Frimpong on June 10, 2022.
 * https://github.com/kakyire
 */
class GetNotesUseCase @Inject constructor(private val notesRepository: NotesRepository) {

    /**
     * Group the notes by [Note.createdOn]
     * return  flow list of [NoteHeaders]
     */
    operator fun invoke() = flow {
        val groupedNotes = mutableListOf<NoteHeaders>()
        notesRepository.getAllNotes()
            .collect { notes ->
                notes.groupBy {
                    it.createdOn
                }.forEach { (dates, noteList) ->
                    groupedNotes.add(NoteHeaders(dates, noteList))
                }
            }

        emit(groupedNotes.toList())
    }/*.flowOn(Dispatchers.IO)*/
}