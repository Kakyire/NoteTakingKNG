package com.kakyiretechnologies.notetakingkng.domain.model

import androidx.annotation.Keep
import androidx.room.Entity

/**
 * @author Kakyire
 * Created by Daniel Frimpong on June 08, 2022.
 * https://github.com/kakyire
 */
@Keep
@Entity(tableName = "Notes")
data class NotesDTO(
    val title: String,
    val content: String,
    val createdOn: String,
    val modifiedOn: String
)
