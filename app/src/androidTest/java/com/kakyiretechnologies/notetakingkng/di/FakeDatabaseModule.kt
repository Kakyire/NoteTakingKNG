package com.kakyiretechnologies.notetakingkng.di

import android.content.Context
import androidx.room.Room
import com.kakyiretechnologies.notetakingkng.data.db.NoteDatabase
import com.kakyiretechnologies.notetakingkng.domain.utils.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

/**
 * @author Kakyire
 * Created by Daniel Frimpong on June 10, 2022.
 * https://github.com/kakyire
 */
@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DatabaseModule::class]
)
class FakeDatabaseModule {


    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): NoteDatabase {
        return Room.inMemoryDatabaseBuilder(
            context.applicationContext,
            NoteDatabase::class.java,
        )
            .build()

    }

    @Singleton
    @Provides
    fun provideNoteDao(db: NoteDatabase) = db.noteDao()
}