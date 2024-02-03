package com.maksim.mynotes.domain.useCase

import androidx.lifecycle.LiveData
import com.maksim.mynotes.domain.note.Note
import com.maksim.mynotes.domain.note.NoteRepository

class ObserveAllNotesUseCase(private val noteRepository: NoteRepository) {

    fun execute(): LiveData<List<Note>> = noteRepository.observeAllNotes()
}