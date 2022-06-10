package com.kakyiretechnologies.notetakingkng.domain.model

import androidx.annotation.Keep
import java.io.Serializable

/**
 * @author Kakyire
 * Created by Daniel Frimpong on June 08, 2022.
 * https://github.com/kakyire
 */
@Keep
data class Notes(
    val id: String,
    var title: String,
    var content: String,
    var createdOn: String,
    var modifiedOn: String
) : Serializable


fun main() {
    val listOfNotes = mutableListOf<Notes>()

    for (i in 1..6) {
        val createdOn = when {
            i % 2 == 0 -> "June 2, 2022"
            i % 3 == 0 -> "June 3, 2022"
            else -> "June 1, 2022"
        }

        val note = Notes(
            id = "$i",
            title = "title$i",
            content = "content$i",
            modifiedOn = "June 7, 2022",
            createdOn = createdOn
        )

        listOfNotes.add(note)


    }

     listOfNotes.groupBy {
        it.createdOn
    }.mapValues { (dates, notes) ->
         val noteHeaders =NoteHeaders(dates,notes)
        println(noteHeaders)
        println()
    }
}