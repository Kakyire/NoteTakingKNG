package com.kakyiretechnologies.notetakingkng.domain.usecases

import com.kakyiretechnologies.notetakingkng.domain.repositories.AuthRepository
import javax.inject.Inject

/**
 * @author Kakyire
 * Created by Daniel Frimpong on June 12, 2022.
 * https://github.com/kakyire
 */
class SignInUseCase @Inject constructor(private val authRepository: AuthRepository) {
    operator fun invoke(){

    }
}