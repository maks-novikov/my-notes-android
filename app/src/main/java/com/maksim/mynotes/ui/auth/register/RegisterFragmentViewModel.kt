package com.maksim.mynotes.ui.auth.register

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maksim.mynotes.data.api.auth.register.RegisterRequest
import com.maksim.mynotes.domain.useCase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterFragmentViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    fun register(username: String, password: String, firstName: String?, lastName: String?) {
        viewModelScope.launch {
            try {
                registerUseCase.execute(RegisterRequest(username, password, firstName, lastName))
            } catch (e: Throwable) {
                Log.e("RegisterViewModel", "Failed register: ${e.message}")
            }
        }
    }
}