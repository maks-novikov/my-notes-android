package com.maksim.mynotes.ui.summary

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maksim.mynotes.domain.note.Note
import com.maksim.mynotes.domain.useCase.ObserveAllNotesUseCase
import com.maksim.mynotes.domain.useCase.SyncNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesSummaryViewModel @Inject constructor(
    private val observeAllNotesUseCase: ObserveAllNotesUseCase,
    private val syncNotesUseCase: SyncNotesUseCase
) : ViewModel() {

    val notesLiveData: LiveData<List<Note>> = observeAllNotesUseCase.execute()

    fun syncNotes() {
        viewModelScope.launch {
            syncNotesUseCase.execute()
        }
    }
}