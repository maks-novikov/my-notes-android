package com.maksim.mynotes.ui.auth.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maksim.mynotes.data.api.auth.login.LoginRequest
import com.maksim.mynotes.domain.useCase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginFragmentViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    data class LoginViewState(val loginSuccess: Boolean)

    fun login(username: String, password: String) {

        viewModelScope.launch {
            try {
                loginUseCase.execute(LoginRequest(username, password))
            }catch (e: Throwable){
                Log.e("LoginFragmentViewModel", "Failed login: ${e.message}")
            }
        }
    }
}