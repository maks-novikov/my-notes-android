package com.maksim.mynotes.domain.useCase

import com.maksim.mynotes.domain.note.NoteRepository

class DeleteNoteUseCase(private val noteRepository: NoteRepository) {

    suspend fun execute(id: Long) {
        noteRepository.deleteNote(id)
    }
}