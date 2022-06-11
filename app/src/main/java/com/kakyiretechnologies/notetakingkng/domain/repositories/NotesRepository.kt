package com.kakyiretechnologies.notetakingkng.domain.repositories

import com.kakyiretechnologies.notetakingkng.domain.model.Note
import kotlinx.coroutines.flow.Flow

/**
 * @author Kakyire
 * Created by Daniel Frimpong on June 09, 2022.
 * https://github.com/kakyire
 */
interface NotesRepository {

    fun getAllNotes(): Flow<List<Note>>

    fun searchNotes(query: String): Flow<List<Note>>

    suspend fun addNote(note: Note)

    suspend fun updateNote(note: Note)

    suspend fun deleteNote(note: Note)
}