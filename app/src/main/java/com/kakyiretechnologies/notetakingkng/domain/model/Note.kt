package com.kakyiretechnologies.notetakingkng.domain.model

import androidx.annotation.Keep
import com.kakyiretechnologies.notetakingkng.domain.utils.getCurrentDate
import java.io.Serializable
import java.util.*

/**
 * @author Kakyire
 * Created by Daniel Frimpong on June 08, 2022.
 * https://github.com/kakyire
 */
@Keep
data class Note(
    val id: String = UUID.randomUUID().toString(),
    var title: String = "",
    var content: String = "",
    var createdOn: String = getCurrentDate(),
    var modifiedOn: Long = System.currentTimeMillis(),
    var voiceNote: String = ""
) : Serializable


