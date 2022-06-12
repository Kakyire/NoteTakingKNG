package com.kakyiretechnologies.notetakingkng.presentation.utils.extensions

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

/**
 * @author Kakyire
 * Created by Daniel Frimpong on June 09, 2022.
 * https://github.com/kakyire
 */

fun Fragment.navigateToNextPage(direction: NavDirections) {
    findNavController().navigate(direction)
}

fun Fragment.showMessage(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun <T> Fragment.observeLiveData(liveData: LiveData<T>, onChanged: (T) -> Unit) {
    liveData.observe(viewLifecycleOwner) { onChanged(it) }
}