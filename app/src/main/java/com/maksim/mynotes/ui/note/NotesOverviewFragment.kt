package com.maksim.mynotes.ui.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.maksim.mynotes.databinding.FragmentNotesOverviewBinding
import com.maksim.mynotes.ui.base.BaseFragment

class NotesOverviewFragment : BaseFragment() {

    private lateinit var binding: FragmentNotesOverviewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotesOverviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }
}