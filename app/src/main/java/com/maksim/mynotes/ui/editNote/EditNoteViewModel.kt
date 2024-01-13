package com.maksim.mynotes.ui.editNote

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maksim.mynotes.data.api.notes.CreateNoteRequest
import com.maksim.mynotes.domain.note.Note
import com.maksim.mynotes.domain.useCase.CreateEmptyNoteUseCase
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
    private val createEmptyNoteUseCase: CreateEmptyNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase
) : ViewModel() {

    lateinit var noteLiveData: LiveData<Note>

    init {
        viewModelScope.launch {
            //Take noteId from args, if it null create new empty note and get it's id, observe
            // the id from args or the new created empty note id.
            val tmpId = state.get<Int>(NOTE_ID)
            val noteId = if (tmpId == null) {
                val id = createEmptyNoteUseCase.execute()
                state[NOTE_ID] = id
                id
            } else {
                tmpId
            }

            noteLiveData = observeNoteUseCase.execute(noteId)
        }
    }

    fun saveNote(title: String, description: String) {
        var currentNote = noteLiveData.value

        if (currentNote != null) {
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
        }
    }
}