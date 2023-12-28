package com.maksim.mynotes.domain.useCase

import com.maksim.mynotes.data.api.auth.login.LoginRequest
import com.maksim.mynotes.data.api.auth.login.LoginResponse
import com.maksim.mynotes.domain.AsyncResult
import com.maksim.mynotes.domain.AuthRepository

class LoginUseCase(private val authRepository: AuthRepository) {

    suspend fun execute(loginRequest: LoginRequest): AsyncResult<LoginResponse> {
       return  authRepository.login(loginRequest)
    }
}