package com.kakyiretechnologies.notetakingkng.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kakyiretechnologies.notetakingkng.data.db.NoteDatabase
import com.kakyiretechnologies.notetakingkng.domain.utils.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author Kakyire
 * Created by Daniel Frimpong on June 09, 2022.
 * https://github.com/kakyire
 */
@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): NoteDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            NoteDatabase::class.java,
            DATABASE_NAME
        )
            .build()
    }

    @Singleton
    @Provides
    fun provideNoteDao(db: NoteDatabase) = db.noteDao()

}