package com.maksim.mynotes.domain.useCase

import com.maksim.mynotes.domain.note.Note
import com.maksim.mynotes.domain.note.NoteRepository

class UpdateNoteUseCase(private val noteRepository: NoteRepository) {

    suspend fun execute(note: Note){

    }

}