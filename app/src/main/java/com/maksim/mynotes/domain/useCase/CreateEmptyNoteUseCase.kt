package com.maksim.mynotes.domain.useCase

import com.maksim.mynotes.domain.note.NoteRepository

class CreateEmptyNoteUseCase(private val noteRepository: NoteRepository) {

    suspend fun execute():Int{
        return  1
    }
}