package com.maksim.mynotes.ui.auth.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maksim.mynotes.data.api.auth.register.RegisterRequest
import com.maksim.mynotes.domain.AsyncResult
import com.maksim.mynotes.domain.useCase.RegisterUseCase
import com.maksim.mynotes.ui.auth.login.LoginFragmentViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class RegisterFragmentViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    data class RegisterViewState(
        val loginSuccess: Boolean = false,
        val isLoading: Boolean = true,
        val error: String? = null
    )

    private val _registerViewState = MutableLiveData<RegisterViewState>()
    val registerViewState: LiveData<RegisterViewState> = _registerViewState

    fun register(username: String, password: String, firstName: String?, lastName: String?) {
        viewModelScope.launch {

            val request = RegisterRequest(username, password, firstName, lastName)
            when (val result = registerUseCase.execute(request)) {
                is AsyncResult.Data -> {
                    _registerViewState.postValue(RegisterViewState(loginSuccess = true))
                }

                is AsyncResult.Error -> {
                    _registerViewState.postValue(RegisterViewState(error = result.error.message))
                }
            }

        }
    }
}