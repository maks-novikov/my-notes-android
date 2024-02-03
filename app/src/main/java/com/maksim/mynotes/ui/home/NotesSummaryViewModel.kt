package com.maksim.mynotes.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.maksim.mynotes.domain.note.Note
import com.maksim.mynotes.domain.useCase.ObserveAllNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotesSummaryViewModel @Inject constructor(
    private val observeAllNotesUseCase: ObserveAllNotesUseCase
): ViewModel() {

    val notesLiveData: LiveData<List<Note>> = observeAllNotesUseCase.execute()
}