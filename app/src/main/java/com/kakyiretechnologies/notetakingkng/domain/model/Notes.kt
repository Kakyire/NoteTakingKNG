package com.kakyiretechnologies.notetakingkng.domain.model

import androidx.annotation.Keep
import androidx.room.Entity
import java.io.Serializable

/**
 * @author Kakyire
 * Created by Daniel Frimpong on June 08, 2022.
 * https://github.com/kakyire
 */
@Keep
data class Notes(
    val id:String,
    var title: String,
    var content: String,
    var createdOn: String,
    var modifiedOn: String
):Serializable
