package com.kakyiretechnologies.notetakingkng.domain.usecases

import com.kakyiretechnologies.notetakingkng.domain.repositories.NotesRepository
import javax.inject.Inject

/**
 * @author Kakyire
 * Created by Daniel Frimpong on June 10, 2022.
 * https://github.com/kakyire
 */
class GetNotesUseCase @Inject constructor(private val notesRepository: NotesRepository) {

    operator fun invoke(){
        notesRepository.getAllNotes()
    }
}