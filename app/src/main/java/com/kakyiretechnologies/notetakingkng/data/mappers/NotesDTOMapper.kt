package com.kakyiretechnologies.notetakingkng.data.mappers

import com.kakyiretechnologies.notetakingkng.data.model.NotesDTO
import com.kakyiretechnologies.notetakingkng.domain.model.Note
import javax.inject.Inject

/**
 * @author Kakyire
 * Created by Daniel Frimpong on June 09, 2022.
 * https://github.com/kakyire
 */
class NotesDTOMapper @Inject constructor() {

    fun notesDTOtoNotes(notesDTO: NotesDTO): Note {
        return Note(
            id = notesDTO.id,
            title = notesDTO.title,
            content = notesDTO.content,
            modifiedOn = notesDTO.modifiedOn,
            createdOn = notesDTO.createdOn,
            voiceNote = notesDTO.voiceNote
        )
    }


    fun notesToNotesDTO(note: Note): NotesDTO {
        return NotesDTO(
            id = note.id,
            title = note.title,
            content = note.content,
            modifiedOn = note.modifiedOn,
            createdOn = note.createdOn,
            voiceNote = note.voiceNote
        )
    }
}