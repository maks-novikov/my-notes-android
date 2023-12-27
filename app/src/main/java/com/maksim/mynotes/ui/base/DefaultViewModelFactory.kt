package com.maksim.mynotes.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.maksim.mynotes.ui.auth.register.RegisterFragmentViewModel
import com.maksim.mynotes.ui.di.AppContainer

class DefaultViewModelFactory(private val appContainer: AppContainer) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return when {
            modelClass.isAssignableFrom(RegisterFragmentViewModel::class.java) -> initRegisterViewModel()

            else -> throw IllegalArgumentException("Unknown ViewModel class")
        } as T
    }

    private fun initRegisterViewModel(): RegisterFragmentViewModel {
        return RegisterFragmentViewModel(appContainer.provideRegisterUseCase())
    }
}