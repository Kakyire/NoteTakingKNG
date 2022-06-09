package com.kakyiretechnologies.notetakingkng.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kakyiretechnologies.notetakingkng.data.model.NotesDTO
import com.kakyiretechnologies.notetakingkng.domain.utils.DATABASE_VERSION

/**
 * @author Kakyire
 * Created by Daniel Frimpong on June 09, 2022.
 * https://github.com/kakyire
 */
@Database(
    entities = [NotesDTO::class],
    version = DATABASE_VERSION
)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
}