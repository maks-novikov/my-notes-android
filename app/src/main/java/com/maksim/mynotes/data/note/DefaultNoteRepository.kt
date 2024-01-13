package com.maksim.mynotes.data.note

import android.provider.ContactsContract.Data
import androidx.lifecycle.LiveData
import com.maksim.mynotes.data.api.notes.CreateNoteRequest
import com.maksim.mynotes.data.api.notes.NotesService
import com.maksim.mynotes.data.db.NoteDao
import com.maksim.mynotes.data.model.NoteEntity
import com.maksim.mynotes.domain.AsyncResult
import com.maksim.mynotes.domain.note.Note
import com.maksim.mynotes.domain.note.NoteMapper
import com.maksim.mynotes.domain.note.NoteRepository
import java.lang.IllegalStateException

class DefaultNoteRepository(
    private val notesService: NotesService,
    private val noteDao: NoteDao,
    private val mapper: NoteMapper
) : NoteRepository {
    override suspend fun getNotes(): AsyncResult<List<Note>> {
        return when (val response = notesService.getAllNotes()) {
            is AsyncResult.Data -> AsyncResult.success(mapper.responseToNote(response.data))
            is AsyncResult.Error -> AsyncResult.error(response.error)
            else -> throw IllegalStateException("")
        }
    }

    override suspend fun getNote(id: Int): AsyncResult<Note> {
        return when (val response = notesService.getNote(id)) {
            is AsyncResult.Data -> AsyncResult.success(mapper.responseToNote(response.data))
            is AsyncResult.Error -> AsyncResult.error(response.error)
            else -> throw IllegalStateException("")
        }
    }

    override suspend fun createNote(noteRequest: CreateNoteRequest): AsyncResult<Unit> {
        val response = notesService.createNote(noteRequest)
        return if (response is AsyncResult.Data) {

            AsyncResult.success(Unit)
        } else if (response is AsyncResult.Error) {
            AsyncResult.error(response.error)
        }

        /* return notesService.createNote(noteRequest).also {
             if (it is AsyncResult.Data) {
                 noteDao.create()
             }
         }*/
    }

    override fun observeNote(id: Int): LiveData<Note> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNote(id: Int): AsyncResult<Unit> {
        return notesService.deleteNote(id)
    }
}