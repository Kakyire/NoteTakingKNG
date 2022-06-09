package com.kakyiretechnologies.notetakingkng.data.mappers

import com.kakyiretechnologies.notetakingkng.data.model.NotesDTO
import com.kakyiretechnologies.notetakingkng.domain.model.Notes

/**
 * @author Kakyire
 * Created by Daniel Frimpong on June 09, 2022.
 * https://github.com/kakyire
 */
class NotesDTOMapper {

    fun NotesDTO.toNotes(): Notes {
        return Notes(
            id = id,
            title = title,
            content = content,
            modifiedOn = modifiedOn,
            createdOn = createdOn
        )
    }

    fun notesToNotesDTO(notes: Notes): NotesDTO {
        return NotesDTO(
            title = notes.title,
            content = notes.content,
            modifiedOn = notes.modifiedOn,
            createdOn = notes.createdOn
        )
    }
}