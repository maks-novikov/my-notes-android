package com.maksim.mynotes.ui.editNote

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maksim.mynotes.domain.note.Note
import com.maksim.mynotes.domain.useCase.ObserveNoteUseCase
import com.maksim.mynotes.domain.useCase.SaveNoteUseCase
import kotlinx.coroutines.launch

class EditNoteViewModel(
    private val noteId: Int? = null,
    private val observeNoteUseCase: ObserveNoteUseCase,
    private val saveNoteUseCase: SaveNoteUseCase
) : ViewModel() {

    private val _noteLiveData = observeNoteUseCase.execute(noteId)
    val noteLiveData: LiveData<Note?> = _noteLiveData




    fun saveNote(title: String, description: String) {
        var currentNote = _noteLiveData.value
        if(currentNote != null && (currentNote.title != title || currentNote.description != description)){
            currentNote = currentNote.copy(title = title, description = description)
            viewModelScope.launch {
                saveNoteUseCase.execute(currentNote)
            }
        }
    }
}