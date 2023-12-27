package com.maksim.mynotes.data

import com.maksim.mynotes.data.api.auth.AuthService
import com.maksim.mynotes.data.api.auth.login.LoginRequest
import com.maksim.mynotes.data.api.auth.register.RegisterRequest
import com.maksim.mynotes.domain.AuthRepository
import com.maksim.mynotes.domain.session.SessionHolder
import com.maksim.mynotes.domain.session.UserSession

class DefaultAuthRepository(
    private val authService: AuthService,
    private val sessionHolder: SessionHolder
) : AuthRepository {

    override fun getCurrentSession(): UserSession? {
        return sessionHolder.getSession()
    }

    override suspend fun register(registerRequest: RegisterRequest) {
        authService.register(registerRequest).also {
            sessionHolder.setSession(UserSession(it.username, it.token))
        }
    }

    override suspend fun login(loginRequest: LoginRequest) {
        authService.login(loginRequest).also {
            sessionHolder.setSession(UserSession(it.username, it.token))
        }
    }

    override suspend fun logout() {
        sessionHolder.clearSession()
    }
}