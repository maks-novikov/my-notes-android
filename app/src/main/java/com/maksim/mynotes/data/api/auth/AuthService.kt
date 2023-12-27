package com.maksim.mynotes.data.api.auth

import android.util.Log
import com.maksim.mynotes.data.api.auth.login.LoginRequest

import com.maksim.mynotes.data.api.auth.login.LoginResponse
import com.maksim.mynotes.data.api.auth.register.RegisterRequest
import com.maksim.mynotes.data.api.auth.register.RegisterResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthService(private val authApi: AuthApi){

    private val dispatcher = Dispatchers.IO

   /* private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.0.115:8080/api/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val authApi: AuthApi = retrofit.create(AuthApi::class.java)*/

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