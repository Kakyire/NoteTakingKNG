package com.kakyiretechnologies.notetakingkng.data.repositories

import com.kakyiretechnologies.notetakingkng.data.db.NoteDao
import com.kakyiretechnologies.notetakingkng.data.mappers.NotesDTOMapper
import com.kakyiretechnologies.notetakingkng.data.model.NotesDTO
import com.kakyiretechnologies.notetakingkng.domain.model.Note
import com.kakyiretechnologies.notetakingkng.domain.repositories.NotesRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @author Kakyire
 * Created by Daniel Frimpong on June 09, 2022.
 * https://github.com/kakyire
 */
class NotesRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao,
    private val mapper: NotesDTOMapper
) : NotesRepository {

    override fun getAllNotes() = flow { emit(noteDao.getAllNotes().getNotes()) }

    override fun searchNotes(query: String) =
        flow { emit(noteDao.searchNotes(query).getNotes()) }

    override suspend fun addNote(note: Note) {
        val noteDTO = mapper.notesToNotesDTO(note)
        noteDao.addNote(noteDTO)
    }

    override suspend fun updateNote(note: Note) {
        val noteDTO = mapper.notesToNotesDTO(note)
        noteDao.updateNote(noteDTO)
    }

    override suspend fun deleteNote(note: Note) {
        val noteDTO = mapper.notesToNotesDTO(note)
        noteDao.deleteNote(noteDTO)
    }


    private fun List<NotesDTO>.getNotes(): List<Note> {
        val notes = mutableListOf<Note>()
        forEach {
            mapper.apply {
                notes.add(it.toNotes())
            }
        }
        return notes
    }
}