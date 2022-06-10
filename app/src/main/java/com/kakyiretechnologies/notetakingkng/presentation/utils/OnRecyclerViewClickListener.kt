package com.kakyiretechnologies.notetakingkng.presentation.utils

import com.kakyiretechnologies.notetakingkng.domain.model.Notes

/**
 * @author Kakyire
 * Created by Daniel Frimpong on June 10, 2022.
 * https://github.com/kakyire
 */
interface OnRecyclerViewClickListener {
fun onItemClick(note:Notes)
}