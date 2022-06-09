package com.kakyiretechnologies.notetakingkng.data.model

import java.io.Serializable

/**
 * @author Kakyire
 * Created by Daniel Frimpong on June 08, 2022.
 * https://github.com/kakyire
 */
data class Notes(
    val title: String,
    val content: String,
    val createdOn: String,
    val modifiedOn: String
):Serializable
