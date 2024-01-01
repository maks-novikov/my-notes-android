package com.maksim.mynotes.domain.useCase

import com.maksim.mynotes.domain.AuthRepository
class LogoutUseCase(private val authRepository: AuthRepository) {

    suspend fun execute() {
        //TODO clear other data storages

        authRepository.logout()
    }
}