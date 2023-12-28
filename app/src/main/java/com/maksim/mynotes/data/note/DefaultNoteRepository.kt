package com.maksim.mynotes.data.note

import com.maksim.mynotes.data.api.notes.NotesService
import com.maksim.mynotes.domain.AsyncResult
import com.maksim.mynotes.domain.note.NoteRepository

class DefaultNoteRepository(private val notesService: NotesService): NoteRepository {
    override suspend fun createNote(): AsyncResult<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNote(id: Int): AsyncResult<Unit> {
        TODO("Not yet implemented")
    }
}