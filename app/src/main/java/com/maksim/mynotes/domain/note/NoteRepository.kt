package com.maksim.mynotes.domain.note

import com.maksim.mynotes.data.api.notes.CreateNoteRequest
import com.maksim.mynotes.data.api.notes.NoteResponse
import com.maksim.mynotes.domain.AsyncResult

interface NoteRepository {

    suspend fun getNotes(): AsyncResult<List<NoteResponse>>
    suspend fun getNote(id: Int): AsyncResult<NoteResponse?>
    suspend fun createNote(noteRequest: CreateNoteRequest): AsyncResult<Unit>
    suspend fun deleteNote(id: Int): AsyncResult<Unit>
}