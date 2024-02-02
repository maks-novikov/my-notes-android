package com.maksim.mynotes.ui.editNote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maksim.mynotes.data.api.notes.CreateNoteRequest
import com.maksim.mynotes.domain.AsyncResult
import com.maksim.mynotes.domain.note.Note
import com.maksim.mynotes.domain.useCase.CreateNoteUseCase
import com.maksim.mynotes.domain.useCase.ObserveNoteUseCase
import com.maksim.mynotes.domain.useCase.UpdateNoteUseCase
import com.maksim.mynotes.ui.editNote.EditNoteFragment.Companion.NOTE_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class EditNoteViewModel @Inject constructor(
    private val state: SavedStateHandle,
    private val observeNoteUseCase: ObserveNoteUseCase,
    private val creteNoteUseCase: CreateNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase
) : ViewModel() {

    private val _noteLiveData = MutableLiveData<Note>()
    var noteLiveData: LiveData<Note?>? = null

    init {
        viewModelScope.launch {
            //Take noteId from args, if it null create new empty note and get it's id, observe
            // the id from args or the new created empty note id.
            val noteId = state.get<Long>(NOTE_ID)
            if (noteId != null)
                observeNote(noteId)
        }
    }

    private fun observeNote(noteId: Long) {
        noteLiveData = observeNoteUseCase.execute(noteId)
    }


    fun saveNote(title: String, description: String) {
        viewModelScope.launch {
            var currentNote = noteLiveData?.value
            if (currentNote != null) {
                if (currentNote.title != title || currentNote.description != description) {
                    currentNote = currentNote.copy(title = title, description = description)
                    updateNoteUseCase.execute(currentNote)
                }
            } else {
                val createResponse = creteNoteUseCase.execute(
                    CreateNoteRequest(
                        title,
                        description,
                        Date().time.toString()
                    )
                )

                when (createResponse) {
                    is AsyncResult.Data -> {
                        state.set(NOTE_ID, createResponse.data)
                        observeNote(createResponse.data)
                    }

                    is AsyncResult.Error -> {}
                }

            }
        }

        /*if (currentNote != null) {
            //Content of note updated
            currentNote = currentNote.copy(title = title, description = description)
            if (currentNote.title != title || currentNote.description != description) {
                viewModelScope.launch {
                    updateNoteUseCase.execute(currentNote)
                }
            }
        } else {
            viewModelScope.launch {
                creteNoteUseCase.execute(
                    CreateNoteRequest(
                        title,
                        description,
                        Date().time.toString()
                    )
                )
            }
        }*/
    }
}