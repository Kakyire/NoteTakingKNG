package com.kakyiretechnologies.notetakingkng.domain.usecases

import com.google.common.truth.Truth.assertThat
import com.kakyiretechnologies.notetakingkng.domain.model.Note
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

/**
 * @author Kakyire
 * Created by Daniel Frimpong on June 11, 2022.
 * https://github.com/kakyire
 */
@HiltAndroidTest
class NoteUseCasesAndroidTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var getNotesUseCase: GetNotesUseCase

    @Inject
    lateinit var addNotesUseCase: AddNotesUseCase

    @Inject
    lateinit var searchNotesUseCase: SearchNotesUseCase

    @Inject
    lateinit var getNoteDetailsUseCase: GetNoteDetailsUseCase

    @Inject
    lateinit var deleteNotesUseCase: DeleteNotesUseCase

    @Inject
    lateinit var updateNotesUseCase: UpdateNotesUseCase

    @Before
    fun setUp() {
        hiltRule.inject()

        runBlocking {
            for (i in 1..5) {

                val note = Note(
                    id = "$i",
                    title = "title$i",
                    content = "content$i",
                    modifiedOn = "June 7, 2022",
                    createdOn = "June $i, 2022"
                )
                addNotesUseCase.invoke(note)
            }
        }

    }

    @Test
    fun getNotes_fromDatabase() = runBlocking {


        getNotesUseCase.invoke()
            .collect {
                assertThat(it.size).isEqualTo(5)
            }


    }


    @Test
    fun searchNoteTitle_andReturnNonEmptyList() = runBlocking {
        val createdOn = "June 3, 2022"
        searchNotesUseCase.invoke(createdOn)
            .collect { assertThat(it).isNotEmpty() }
    }

    @Test
    fun searchNoteCreatedDate_andReturnNonEmptyList() = runBlocking {
        val title = "title3"
        searchNotesUseCase.invoke(title)
            .collect { assertThat(it).isNotEmpty() }
    }

    @Test
    fun deleteNote_fromDatabase() = runBlocking {
        val note = Note(
            id = "4",
            title = "title4",
            content = "content4",
            modifiedOn = "June 7, 2022",
            createdOn = "June 4, 2022"
        )
        deleteNotesUseCase.invoke(note)

        getNotesUseCase.invoke().collect { assertThat(it).doesNotContain(note) }
    }

    @Test
    fun updateNote_inDatabase() = runBlocking {

        val title = "new title"

        val note = Note(
            id = "4",
            title = title,
            content = "new content",
            modifiedOn = "June 23, 2022",
            createdOn = "June 4, 2022"
        )
        updateNotesUseCase.invoke(note)

        searchNotesUseCase.invoke(title)
            .collect { assertThat(it).isNotEmpty() }


    }

    @Test
    fun returnNote_withSpecificId()= runBlocking {
        val note = Note(
            id = "2",
            title = "title2",
            content = "content2",
            modifiedOn = "June 7, 2022",
            createdOn = "June 2, 2022"
        )

            getNoteDetailsUseCase.invoke("2").collect{
                assertThat(it).isEqualTo(note)
            }


    }
}