package com.kakyiretechnologies.notetakingkng.domain.model

/**
 * @author Kakyire
 * Created by Daniel Frimpong on June 10, 2022.
 * https://github.com/kakyire
 */

data class NoteHeaders(
    val date: String,
    val notes: List<Notes>
)