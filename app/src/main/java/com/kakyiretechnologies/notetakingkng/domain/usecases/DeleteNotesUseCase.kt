package com.kakyiretechnologies.notetakingkng.domain.usecases

import com.kakyiretechnologies.notetakingkng.domain.model.Note
import com.kakyiretechnologies.notetakingkng.domain.repositories.NotesRepository
import javax.inject.Inject

/**
 * @author Kakyire
 * Created by Daniel Frimpong on June 10, 2022.
 * https://github.com/kakyire
 */
class DeleteNotesUseCase @Inject constructor(private val notesRepository: NotesRepository) {
    suspend operator fun invoke(note: Note) = notesRepository.deleteNote(note)
}