package com.maksim.mynotes.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.maksim.mynotes.databinding.FragmentNotesSummaryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotesSummaryFragment : Fragment() {

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

        observeState()

    }

    private fun observeState() {
        viewModel.notesLiveData.observe(viewLifecycleOwner) {

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}