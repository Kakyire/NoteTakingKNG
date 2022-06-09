package com.kakyiretechnologies.notetakingkng.data.db

import android.os.Build.VERSION_CODES.S
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.kakyiretechnologies.notetakingkng.data.model.NotesDTO

/**
 * @author Kakyire
 * Created by Daniel Frimpong on June 09, 2022.
 * https://github.com/kakyire
 */
@Dao
interface NoteDao {

    @Query("SELECT * FROM notes")
    fun getAllNotes(): List<NotesDTO>

    @Query("SELECT * FROM notes WHERE created_on LIKE :query OR title  LIKE :query")
    fun searchNotes(query: String): List<NotesDTO>

    @Insert
    suspend fun addNote(note: NotesDTO)

    @Delete
    suspend fun deleteNote(note: NotesDTO)
}