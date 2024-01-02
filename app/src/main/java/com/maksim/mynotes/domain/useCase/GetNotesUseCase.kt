package com.maksim.mynotes.domain.useCase

import com.maksim.mynotes.data.api.notes.NoteResponse
import com.maksim.mynotes.domain.AsyncResult
import com.maksim.mynotes.domain.note.NoteRepository

class GetNotesUseCase(private val notesRepository: NoteRepository) {
    suspend fun execute(): AsyncResult<List<NoteResponse>>{
        return notesRepository.getNotes()
    }
}