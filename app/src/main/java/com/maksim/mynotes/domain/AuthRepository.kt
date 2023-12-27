package com.maksim.mynotes.domain

import com.maksim.mynotes.data.api.auth.login.LoginRequest
import com.maksim.mynotes.data.api.auth.register.RegisterRequest
import com.maksim.mynotes.domain.session.UserSession

interface AuthRepository {
    fun getCurrentSession(): UserSession?
    suspend fun register(registerRequest: RegisterRequest)
    suspend fun login(loginRequest: LoginRequest)
    suspend fun logout()
}