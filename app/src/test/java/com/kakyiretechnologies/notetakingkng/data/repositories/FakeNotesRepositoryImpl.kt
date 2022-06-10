package com.kakyiretechnologies.notetakingkng.data.repositories

import com.kakyiretechnologies.notetakingkng.domain.model.Notes
import com.kakyiretechnologies.notetakingkng.domain.repositories.NotesRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @author Kakyire
 * Created by Daniel Frimpong on June 10, 2022.
 * https://github.com/kakyire
 */
class FakeNotesRepositoryImpl @Inject constructor(private val notes: MutableList<Notes> = mutableListOf()) :
    NotesRepository {


    override fun getAllNotes() = flow { emit(notes) }

    override fun searchNotes(query: String) = flow { emit(notes.filter { it.title == query }.toList()) }

    override suspend fun addNote(note: Notes) {
        notes.add(note)
    }

    override suspend fun updateNote(note: Notes) {
        notes.find {
            it.id == note.id
        }?.apply {
            title=note.title
            content =note.content
            modifiedOn=note.modifiedOn
            createdOn=note.createdOn
        }
    }

    override suspend fun deleteNote(note: Notes) {
        notes.remove(note)
    }
}