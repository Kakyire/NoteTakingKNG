package com.kakyiretechnologies.notetakingkng.data.repositories

import com.google.common.truth.Truth.assertThat
import com.kakyiretechnologies.notetakingkng.domain.model.Note
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

/**
 * @author Kakyire
 * Created by Daniel Frimpong on June 10, 2022.
 * https://github.com/kakyire
 */
class NoteRepositoryImplTest {

    private val notes = mutableListOf<Note>()

    private val notesSize = 5

    private lateinit var repository: FakeNotesRepositoryImpl

    @Before
    fun setup() {

        for (i in 1..notesSize) {

            val note = Note(
                id = "$i",
                title = "title$i",
                content = "content$i",
                modifiedOn = i.toLong(),
                createdOn = "June $i, 2022",
                headerDate = i.toLong()
            )
            notes.add(note)
        }

        repository = FakeNotesRepositoryImpl(notes)
    }


    @Test
    fun `Check if all notes are available`() = runBlocking {

        repository.getAllNotes()
            .collect {
                assertThat(it.size).isEqualTo(5)
            }

    }

    @Test
    fun `Search for note in the available notes`() = runBlocking {
        val title = "title5"
        repository.searchNotes(title)
            .collect { assertThat(it).isNotEmpty() }
    }

    @Test
    fun `Get note detail using note id`()= runBlocking {

        val note = Note(
            id = "2",
            title = "title2",
            content = "content2",
            modifiedOn = 2,
            createdOn = "June 2, 2022",
            headerDate = 2
        )
        repository.getNoteDetails("2").collect{
            assertThat(it).isEqualTo(note)
        }


    }

    @Test
    fun `Add note(s) to the available notes`() = runBlocking {
        val note = Note(
            id = "32",
            title = "title32",
            content = "content32",
            modifiedOn = 32,
            createdOn = "June 6, 2022",
            headerDate = 32
        )
        repository.addNote(note)
        repository.getAllNotes()
            .collect { assertThat(it.size).isEqualTo(6) }


    }

    @Test
    fun `Update note at specific id`() = runBlocking {
        val note = Note(
            id = "3",
            title = "new title",
            content = "new content",
            modifiedOn = 3,
            createdOn = "June 6, 2022",
            headerDate = 3
        )
        val failExpected = Note(
            id = "3",
            title = "this will fail",
            content = "wrong content",
            modifiedOn =3,
            createdOn = "June 6, 2022",
            headerDate = 3
        )

        repository.updateNote(note)
        repository.getAllNotes().collect {
            assertThat(it).contains(note)
        }
    }

    @Test
    fun `Delete note from the available notes`() = runBlocking {


        repository.deleteNote(notes[3])

        repository.getAllNotes()
            .collect {
                assertThat(it.size).isEqualTo(4)
            }
    }
}