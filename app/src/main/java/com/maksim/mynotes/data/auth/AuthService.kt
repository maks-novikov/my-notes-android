package com.maksim.mynotes.data.auth

import android.util.Log
import com.maksim.mynotes.api.client.AuthApi
import com.maksim.mynotes.api.request.LoginRequest
import com.maksim.mynotes.api.request.RegisterRequest
import com.maksim.mynotes.api.response.LoginResponse
import com.maksim.mynotes.api.response.RegisterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AuthService {

    private val dispatcher = Dispatchers.IO

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.0.115:8080/api/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val authApi: AuthApi = retrofit.create(AuthApi::class.java)

    suspend fun login(request: LoginRequest): LoginResponse {
        return withContext(dispatcher) {
            try {
                val data = authApi.login(request)
                Log.d("LoginResponse", "login data: $data")
                data
            } catch (e: Throwable) {
                Log.e("LoginError", "error: $e")
                throw e
            }
        }
    }

    suspend fun register(request: RegisterRequest): RegisterResponse {
        return withContext(dispatcher){
            try {
                val data = authApi.register(request)
                Log.d("RegisterResponse", "register data: $data")
                data
            }catch (e: Throwable){
                Log.e("RegisterError", "error: $e")
                throw e
            }
        }
    }
}