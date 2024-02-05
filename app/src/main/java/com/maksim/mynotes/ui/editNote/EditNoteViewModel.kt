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
import com.maksim.mynotes.domain.useCase.DeleteNoteUseCase
import com.maksim.mynotes.domain.useCase.ObserveNoteUseCase
import com.maksim.mynotes.domain.useCase.UpdateNoteUseCase
import com.maksim.mynotes.ui.editNote.EditNoteFragment.Companion.NOTE_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class EditNoteViewModel @Inject constructor(
    private val state: SavedStateHandle,
    private val observeNoteUseCase: ObserveNoteUseCase,
    private val creteNoteUseCase: CreateNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModel() {

    private val _noteLiveData = MutableLiveData<Note>()
    var noteLiveData: LiveData<Note?>? = null

    init {

        state.get<Long>(NOTE_ID)?.let {
            observeNote(it)
        }
    }

    private fun observeNote(noteId: Long) {
        noteLiveData = observeNoteUseCase.execute(noteId)
    }

    fun saveNote(title: String, description: String) {
        MainScope().launch {

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
    }

    fun deleteNote() {
        val id = state.get<Long>(NOTE_ID)
        if (id != null) {
            MainScope().launch {
                deleteNoteUseCase.execute(id)
            }
        }
    }
}