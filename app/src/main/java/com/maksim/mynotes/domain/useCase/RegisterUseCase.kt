package com.maksim.mynotes.domain.useCase

import com.maksim.mynotes.data.api.auth.register.RegisterRequest
import com.maksim.mynotes.domain.AuthRepository

class RegisterUseCase(private val authRepository: AuthRepository) {

    suspend fun execute(registerRequest: RegisterRequest){
        authRepository.register(registerRequest)
    }
}