package com.maksim.mynotes.domain.useCase

import com.maksim.mynotes.data.api.notes.CreateNoteRequest
import com.maksim.mynotes.domain.note.NoteRepository

class CreateNoteUseCase(private val noteRepository: NoteRepository) {

    suspend fun execute(noteRequest: CreateNoteRequest) {
        noteRepository.createNote(noteRequest)
    }
}