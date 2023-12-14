package com.maksim.mynotes.api.client

import com.maksim.mynotes.api.request.LoginRequest
import com.maksim.mynotes.api.request.RegisterRequest
import com.maksim.mynotes.api.response.LoginResponse
import com.maksim.mynotes.api.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("register")
    suspend fun register(@Body request: RegisterRequest): RegisterResponse
}