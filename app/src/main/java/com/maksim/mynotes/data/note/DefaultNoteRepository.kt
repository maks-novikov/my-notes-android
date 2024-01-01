package com.maksim.mynotes.data.note

import com.maksim.mynotes.data.api.notes.CreateNoteRequest
import com.maksim.mynotes.data.api.notes.NoteResponse
import com.maksim.mynotes.data.api.notes.NotesService
import com.maksim.mynotes.domain.AsyncResult
import com.maksim.mynotes.domain.note.NoteRepository

class DefaultNoteRepository(private val notesService: NotesService): NoteRepository {
    override suspend fun getNotes(): AsyncResult<List<NoteResponse>> {
        return notesService.getAllNotes()
    }
    override suspend fun getNote(id: Int): AsyncResult<NoteResponse?> {
        return notesService.getNote(id)
    }
    override suspend fun createNote(noteRequest: CreateNoteRequest): AsyncResult<Unit> {
        return notesService.createNote(noteRequest)
    }

    override suspend fun deleteNote(id: Int): AsyncResult<Unit> {
        return notesService.deleteNote(id)
    }
}