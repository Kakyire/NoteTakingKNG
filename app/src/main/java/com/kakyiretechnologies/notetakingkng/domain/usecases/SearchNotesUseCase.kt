package com.kakyiretechnologies.notetakingkng.domain.usecases

import com.kakyiretechnologies.notetakingkng.domain.model.NoteHeaders
import com.kakyiretechnologies.notetakingkng.domain.repositories.NotesRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @author Kakyire
 * Created by Daniel Frimpong on June 10, 2022.
 * https://github.com/kakyire
 */
class SearchNotesUseCase @Inject constructor(private val notesRepository: NotesRepository) {

    operator fun invoke(query:String)= flow{
        val groupedNotes = mutableListOf<NoteHeaders>()
        notesRepository.searchNotes(query)
            .collect { notes ->
                notes.groupBy {
                    it.createdOn
                }.forEach { (dates, noteList) ->
                    groupedNotes.add(NoteHeaders(dates, noteList))
                }
            }

        emit(groupedNotes.toList())
    }
}