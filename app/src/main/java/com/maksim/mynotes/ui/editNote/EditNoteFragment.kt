package com.maksim.mynotes.ui.editNote

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.maksim.mynotes.databinding.FragmentEditNoteBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Timer

import java.util.TimerTask


@AndroidEntryPoint
class EditNoteFragment : Fragment() {

    companion object {
        const val NOTE_ID = "noteId"
    }

    private val saveNoteTimer = Timer()
    private val viewModel by viewModels<EditNoteViewModel>()

    private var _binding: FragmentEditNoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeState()
    }

    private fun saveNote() {

        showMessage("Saving...")
        val title = binding.noteTitleInput.text.toString().trim()
        val description = binding.noteDescriptionInput.text.toString().trim()

        viewModel.saveNote(title, description)
    }


    private fun showMessage(message: String) {
        CoroutineScope(Dispatchers.Main).launch {
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun observeState() {

        viewModel.noteLiveData?.observe(viewLifecycleOwner) { note ->
            Log.d("EditNoteFragmentOnNote", "Note -> ${note?.title}")

            note?.let {
                binding.noteTitleInput.setText(it.title)
                binding.noteDescriptionInput.setText(it.description)
            }
        }
    }

    override fun onDestroyView() {
        saveNoteTimer.cancel()
        saveNote()
        super.onDestroyView()
        _binding = null
    }
}