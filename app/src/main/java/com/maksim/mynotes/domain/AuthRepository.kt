package com.maksim.mynotes.domain

import com.maksim.mynotes.data.api.auth.AuthService
import com.maksim.mynotes.data.api.auth.login.LoginRequest
import com.maksim.mynotes.data.api.auth.register.RegisterRequest
import com.maksim.mynotes.domain.session.SessionHolder
import com.maksim.mynotes.domain.session.UserSession

class AuthRepository(
    private val authService: AuthService,
    private val sessionHolder: SessionHolder
) {

    fun getCurrentSession(): UserSession? {
        return sessionHolder.getSession()
    }


    suspend fun register(registerRequest: RegisterRequest) {
        authService.register(registerRequest).also {
            sessionHolder.setSession(UserSession(it.email, it.token))
        }
    }

    suspend fun login(loginRequest: LoginRequest) {
        authService.login(loginRequest).also {
            sessionHolder.setSession(UserSession(it.username, it.token))
        }
    }

    suspend fun logout() {
        sessionHolder.clearSession()
    }
}