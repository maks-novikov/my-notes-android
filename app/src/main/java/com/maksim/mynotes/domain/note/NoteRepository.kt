package com.maksim.mynotes.domain.note

import androidx.lifecycle.LiveData
import com.maksim.mynotes.data.api.notes.CreateNoteRequest
import com.maksim.mynotes.domain.AsyncResult

interface NoteRepository {

    suspend fun getNotes(): AsyncResult<List<Note>>
    suspend fun getLocalNotes(): List<Note>
    fun observeAllNotes(): LiveData<List<Note>>
    suspend fun getNote(id: Long): AsyncResult<Note>
    suspend fun createNote(noteRequest: CreateNoteRequest): AsyncResult<Long>
    suspend fun updateNote(note: Note)
    suspend fun updateLocal(note: Note)
    suspend fun deleteNote(id: Long): AsyncResult<Unit>
    suspend fun deleteLocal(id: Long)
    fun observeNote(id: Long): LiveData<Note?>
}