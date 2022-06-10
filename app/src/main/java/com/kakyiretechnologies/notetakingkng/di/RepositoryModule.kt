package com.kakyiretechnologies.notetakingkng.di

import com.kakyiretechnologies.notetakingkng.data.repositories.NotesRepositoryImpl
import com.kakyiretechnologies.notetakingkng.domain.repositories.NotesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * @author Kakyire
 * Created by Daniel Frimpong on June 10, 2022.
 * https://github.com/kakyire
 */
@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindNoteRepository(notesRepositoryImpl: NotesRepositoryImpl):NotesRepository
}