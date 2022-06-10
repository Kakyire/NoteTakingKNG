package com.kakyiretechnologies.notetakingkng.di

import com.kakyiretechnologies.notetakingkng.data.repositories.FakeNotesRepositoryImpl
import com.kakyiretechnologies.notetakingkng.domain.repositories.NotesRepository
import dagger.Binds
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.testing.TestInstallIn

/**
 * @author Kakyire
 * Created by Daniel Frimpong on June 10, 2022.
 * https://github.com/kakyire
 */

@TestInstallIn(
    components = [ViewModelComponent::class],
    replaces = [RepositoryModule::class]
)
abstract class FakeRepositoryModuleTest {

    @Binds
    abstract fun bindNoteRepository(notesRepositoryImpl: FakeNotesRepositoryImpl): NotesRepository

}