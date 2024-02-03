package com.maksim.mynotes.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.maksim.mynotes.R
import com.maksim.mynotes.databinding.FragmentNotesSummaryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesSummaryFragment : Fragment() {

    private val TAG = NotesSummaryFragment::class.java.simpleName

    private var _binding: FragmentNotesSummaryBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<NotesSummaryViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNotesSummaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.createNoteFab.setOnClickListener {
            findNavController().navigate(R.id.nav_edit_note)
        }
        observeState()
    }

    private fun observeState() {
        viewModel.notesLiveData.observe(viewLifecycleOwner) {
            Log.d(TAG, "notes: $it")
            //TODO check only the updated notes and submit to adapter?
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}