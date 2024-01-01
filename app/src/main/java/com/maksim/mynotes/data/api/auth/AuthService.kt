package com.maksim.mynotes.data.api.auth

import android.util.Log
import com.maksim.mynotes.data.api.auth.login.LoginRequest

import com.maksim.mynotes.data.api.auth.login.LoginResponse
import com.maksim.mynotes.data.api.auth.register.RegisterRequest
import com.maksim.mynotes.data.api.auth.register.RegisterResponse
import com.maksim.mynotes.domain.AsyncResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthService(private val authApi: AuthApi) {

    private val dispatcher = Dispatchers.IO

    suspend fun login(request: LoginRequest): AsyncResult<LoginResponse> {
        return withContext(dispatcher) {
            try {
                val data = authApi.login(request)
                Log.d("LoginResponse", "login data: $data")
                AsyncResult.success(data)
            } catch (e: Throwable) {
                Log.e("LoginError", "error: $e")
                AsyncResult.error(e)
            }
        }
    }

    suspend fun register(request: RegisterRequest): AsyncResult<RegisterResponse> {
        return withContext(dispatcher) {
            try {
                val data = authApi.register(request)
                Log.d("RegisterResponse", "register data: $data")
                AsyncResult.success(data)
            } catch (e: Throwable) {
                Log.e("RegisterError", "error: $e")
                AsyncResult.error(e)
            }
        }
    }
}