package com.maksim.mynotes.domain

import com.maksim.mynotes.data.api.auth.login.LoginRequest
import com.maksim.mynotes.data.api.auth.login.LoginResponse
import com.maksim.mynotes.data.api.auth.register.RegisterRequest
import com.maksim.mynotes.data.api.auth.register.RegisterResponse
import com.maksim.mynotes.domain.session.UserSession

interface AuthRepository {
    fun getCurrentSession(): UserSession?
    suspend fun register(registerRequest: RegisterRequest): AsyncResult<RegisterResponse>
    suspend fun login(loginRequest: LoginRequest): AsyncResult<LoginResponse>
    suspend fun logout(): AsyncResult<Unit>
}