package com.maksim.mynotes.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.maksim.mynotes.ui.auth.register.RegisterFragmentViewModel

class DefaultViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return when {
            modelClass.isAssignableFrom(RegisterFragmentViewModel::class.java) -> RegisterFragmentViewModel()

            else -> throw IllegalArgumentException("Unknown ViewModel class")
        } as T

    }
}