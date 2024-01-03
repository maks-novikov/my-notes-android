package com.maksim.mynotes.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maksim.mynotes.domain.AsyncResult
import com.maksim.mynotes.domain.note.Note
import com.maksim.mynotes.domain.useCase.GetNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesSummaryViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase
): ViewModel() {

    private val _notesLiveData = MutableLiveData<List<Note>>()
    val notesLiveData: LiveData<List<Note>> = _notesLiveData

    init {
        viewModelScope.launch {
            when(val response = getNotesUseCase.execute()){
                is AsyncResult.Data -> {
                    _notesLiveData.postValue(response.data)
                }
                is AsyncResult.Error -> {

                    Log.d("NotesSummaryViewModel", "error: ${response.error}")
                }
            }
        }
    }

}