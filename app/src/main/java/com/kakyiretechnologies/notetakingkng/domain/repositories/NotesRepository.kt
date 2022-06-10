package com.kakyiretechnologies.notetakingkng.domain.repositories

import com.kakyiretechnologies.notetakingkng.domain.model.Notes
import kotlinx.coroutines.flow.Flow

/**
 * @author Kakyire
 * Created by Daniel Frimpong on June 09, 2022.
 * https://github.com/kakyire
 */
interface NotesRepository {

    fun getAllNotes(): Flow<List<Notes>>

    fun searchNotes(query: String): Flow<List<Notes>>

    suspend fun addNote(note: Notes)

    suspend fun updateNote(note: Notes)

    suspend fun deleteNote(note: Notes)
}