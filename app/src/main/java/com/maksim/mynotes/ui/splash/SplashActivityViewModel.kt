package com.maksim.mynotes.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maksim.mynotes.domain.session.UserSession
import com.maksim.mynotes.domain.useCase.SyncNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashActivityViewModel @Inject constructor(
    val session: UserSession?,
    private val syncNotesUseCase: SyncNotesUseCase
) : ViewModel() {

    fun synNotes() {
        viewModelScope.launch {
            syncNotesUseCase.execute()
        }
    }
}