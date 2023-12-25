package com.maksim.mynotes.data.api.auth

import com.maksim.mynotes.data.api.auth.login.LoginRequest

import com.maksim.mynotes.data.api.auth.login.LoginResponse
import com.maksim.mynotes.data.api.auth.register.RegisterRequest
import com.maksim.mynotes.data.api.auth.register.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("register")
    suspend fun register(@Body request: RegisterRequest): RegisterResponse
}