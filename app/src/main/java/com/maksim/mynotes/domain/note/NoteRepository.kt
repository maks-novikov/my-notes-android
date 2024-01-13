package com.maksim.mynotes.domain.note

import androidx.lifecycle.LiveData
import com.maksim.mynotes.data.api.notes.CreateNoteRequest
import com.maksim.mynotes.domain.AsyncResult

interface NoteRepository {

    suspend fun getNotes(): AsyncResult<List<Note>>
    suspend fun getNote(id: Int): AsyncResult<Note>
    suspend fun createNote(noteRequest: CreateNoteRequest): AsyncResult<Unit>
    suspend fun deleteNote(id: Int): AsyncResult<Unit>
    fun observeNote(id: Int): LiveData<Note>
}