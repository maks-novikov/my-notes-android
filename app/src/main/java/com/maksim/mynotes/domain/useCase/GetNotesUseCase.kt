package com.maksim.mynotes.domain.useCase

import com.maksim.mynotes.domain.AsyncResult
import com.maksim.mynotes.domain.note.Note
import com.maksim.mynotes.domain.note.NoteRepository

class GetNotesUseCase(private val notesRepository: NoteRepository) {
    suspend fun execute(): AsyncResult<List<Note>>{
        return notesRepository.getNotes()
    }
}