package com.kakyiretechnologies.notetakingkng.data.db

import androidx.room.*
import com.kakyiretechnologies.notetakingkng.data.model.NotesDTO

/**
 * @author Kakyire
 * Created by Daniel Frimpong on June 09, 2022.
 * https://github.com/kakyire
 */
@Dao
interface NoteDao {

    @Query("SELECT * FROM notes ")
    suspend fun getAllNotes(): List<NotesDTO>

    @Query("SELECT * FROM notes WHERE created_on LIKE :query OR title  LIKE :query ORDER BY id DESC ")
    fun searchNotes(query: String): List<NotesDTO>

    @Query("SELECT * FROM notes WHERE id = :id ")
    fun getNoteDetail(id: String): NotesDTO

    @Update
    suspend fun updateNote(note: NotesDTO)

    @Insert
    suspend fun addNote(note: NotesDTO)

    @Delete
    suspend fun deleteNote(note: NotesDTO)
}