package com.maksim.mynotes.ui.auth.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maksim.mynotes.data.api.auth.login.LoginRequest
import com.maksim.mynotes.domain.AsyncResult
import com.maksim.mynotes.domain.useCase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginFragmentViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    data class LoginViewState(
        val loginSuccess: Boolean = false,
        val isLoading: Boolean = true,
        val error: String? = null
    )

    private val _loginViewState = MutableLiveData<LoginViewState>()
    val loginViewState: LiveData<LoginViewState> = _loginViewState

    fun login(username: String, password: String) {

        viewModelScope.launch {
            try {
                when (val result = loginUseCase.execute(LoginRequest(username, password))) {
                    is AsyncResult.Data -> {
                        _loginViewState.postValue(LoginViewState(loginSuccess = true))
                    }

                    is AsyncResult.Error -> {
                        _loginViewState.postValue(LoginViewState(error = result.error.message))
                    }
                }

            } catch (e: Throwable) {
                Log.e("LoginFragmentViewModel", "Failed login: ${e.message}")
            }
        }
    }
}