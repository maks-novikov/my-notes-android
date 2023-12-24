package com.maksim.mynotes.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.maksim.mynotes.R
import com.maksim.mynotes.databinding.FragmentHomeAuthBinding
import com.maksim.mynotes.ui.base.BaseFragment

class HomeAuthFragment : BaseFragment(){

    private lateinit var binding: FragmentHomeAuthBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signInTv.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }

        binding.registerBtn.setOnClickListener {
            findNavController().navigate(R.id.registerFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}