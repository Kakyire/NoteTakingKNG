package com.kakyiretechnologies.notetakingkng.presentation.utils.extensions

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

/**
 * @author Kakyire
 * Created by Daniel Frimpong on June 09, 2022.
 * https://github.com/kakyire
 */

fun Fragment.navigateToNextPage(direction:NavDirections){
    findNavController().navigate(direction)
}