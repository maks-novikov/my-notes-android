package com.maksim.mynotes.domain.useCase

import androidx.lifecycle.LiveData
import com.maksim.mynotes.domain.note.Note
import com.maksim.mynotes.domain.note.NoteRepository

class ObserveNoteUseCase(private val noteRepository: NoteRepository) {

    fun execute(id: Int): LiveData<Note> {
        return noteRepository.observeNote(id)
    }
}