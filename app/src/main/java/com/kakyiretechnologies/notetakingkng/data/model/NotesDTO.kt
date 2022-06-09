package com.kakyiretechnologies.notetakingkng.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

/**
 * @author Kakyire
 * Created by Daniel Frimpong on June 08, 2022.
 * https://github.com/kakyire
 */
@Entity(tableName = "Notes")
data class NotesDTO(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val content: String,
    @ColumnInfo(name = "created_on")
    val createdOn: String,
    @ColumnInfo(name = "modified_on")
    val modifiedOn: String
) : Serializable
