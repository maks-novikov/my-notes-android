package com.maksim.mynotes.data.api.notes

import com.maksim.mynotes.data.api.ApiClient
import com.maksim.mynotes.domain.AsyncResult
import com.maksim.mynotes.domain.note.Note
import kotlinx.coroutines.CoroutineDispatcher

class NotesService(
    private val notesApi: NotesApi,
    private val coroutineDispatcher: CoroutineDispatcher
) : ApiClient(coroutineDispatcher) {

    suspend fun getNote(noteId: Long): AsyncResult<NoteResponse> {
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

    suspend fun updateNote(note: Note) {
        execute {
            notesApi.updateNote(UpdateCardRequest(note.id, note.title, note.description))
        }
    }

    suspend fun deleteNote(noteId: Long): AsyncResult<Unit> {
        return execute {
            notesApi.deleteNote(noteId)
        }
    }
}