package com.maksim.mynotes.data.api.auth

import com.maksim.mynotes.data.api.ApiClient
import com.maksim.mynotes.data.api.auth.login.LoginRequest
import com.maksim.mynotes.data.api.auth.login.LoginResponse
import com.maksim.mynotes.data.api.auth.register.RegisterRequest
import com.maksim.mynotes.data.api.auth.register.RegisterResponse
import com.maksim.mynotes.domain.AsyncResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class AuthService(
    private val authApi: AuthApi,
    private val coroutineDispatcher: CoroutineDispatcher
) : ApiClient(coroutineDispatcher) {

    private val dispatcher = Dispatchers.IO

    suspend fun login(request: LoginRequest): AsyncResult<LoginResponse> {

        return execute {
            authApi.login(request)
        }
    }

    suspend fun register(request: RegisterRequest): AsyncResult<RegisterResponse> {
        return execute {
            authApi.register(request)
        }
    }
}