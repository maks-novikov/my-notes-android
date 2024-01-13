package com.maksim.mynotes.data.api.notes

import com.maksim.mynotes.data.api.ApiClient
import com.maksim.mynotes.domain.AsyncResult
import kotlinx.coroutines.CoroutineDispatcher

class NotesService(
    private val notesApi: NotesApi,
    private val coroutineDispatcher: CoroutineDispatcher
) : ApiClient(coroutineDispatcher) {

    suspend fun getNote(noteId: Int): AsyncResult<NoteResponse> {
        return execute {
            notesApi.getNote(noteId)
        }
    }

    suspend fun getAllNotes(): AsyncResult<List<NoteResponse>> {
        return execute {
            notesApi.getAllNotes()
        }
    }

    suspend fun createNote(noteRequest: CreateNoteRequest): AsyncResult<NoteResponse> {
        return execute {
            notesApi.createNote(noteRequest)
        }
    }

    suspend fun deleteNote(noteId: Int): AsyncResult<Unit> {
        return execute {
            notesApi.deleteNote(noteId)
        }
    }
}