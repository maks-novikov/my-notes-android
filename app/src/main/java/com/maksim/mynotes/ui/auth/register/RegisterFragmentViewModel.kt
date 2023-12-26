package com.maksim.mynotes.ui.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maksim.mynotes.data.api.auth.register.RegisterRequest
import com.maksim.mynotes.domain.useCase.RegisterUseCase
import kotlinx.coroutines.launch

class RegisterFragmentViewModel(private val registerUseCase: RegisterUseCase) : ViewModel() {

    fun register(username: String, password: String, firstName: String?, lastName: String?) {
        viewModelScope.launch {
            registerUseCase.execute(RegisterRequest(username, password, firstName, lastName))
        }
    }
}