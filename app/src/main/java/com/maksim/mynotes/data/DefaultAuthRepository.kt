package com.maksim.mynotes.data

import com.maksim.mynotes.data.api.auth.AuthService
import com.maksim.mynotes.data.api.auth.login.LoginRequest
import com.maksim.mynotes.data.api.auth.login.LoginResponse
import com.maksim.mynotes.data.api.auth.register.RegisterRequest
import com.maksim.mynotes.data.api.auth.register.RegisterResponse
import com.maksim.mynotes.domain.AsyncResult
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

    override suspend fun register(registerRequest: RegisterRequest): AsyncResult<RegisterResponse> {
        return authService.register(registerRequest).also {
            if (it is AsyncResult.Data)
                sessionHolder.setSession(UserSession(it.data.username, it.data.token))
        }
    }

    override suspend fun login(loginRequest: LoginRequest): AsyncResult<LoginResponse> {
        return authService.login(loginRequest).also {
            if (it is AsyncResult.Data)
                sessionHolder.setSession(UserSession(it.data.username, it.data.token))
        }
    }

    override suspend fun logout(): AsyncResult<Unit> {
        sessionHolder.clearSession()
        return AsyncResult.success(Unit)
    }
}