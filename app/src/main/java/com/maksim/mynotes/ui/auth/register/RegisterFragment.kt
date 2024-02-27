package com.maksim.mynotes.ui.auth.register

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.maksim.mynotes.R
import com.maksim.mynotes.databinding.FragmentRegisterBinding
import com.maksim.mynotes.ui.base.BaseFragment
import com.maksim.mynotes.ui.home.HomeActivity

import dagger.hilt.android.AndroidEntryPoint
import java.lang.StringBuilder

@AndroidEntryPoint
class RegisterFragment : BaseFragment() {

    private lateinit var binding: FragmentRegisterBinding
    private val viewModel by viewModels<RegisterFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.submitBtn.setOnClickListener {
            closeKeyboard()
            submitRegister()
        }

        observerState()

    }

    private fun submitRegister() {

        binding.errorsTv.text = ""

        val username = binding.usernameInput.text.toString().trim()
        val firstName = binding.firstNameInput.text.toString().trim()
        val lastName = binding.lastNameInput.text.toString().trim()
        val password = binding.passwordInput.text.toString().trim()
        val repeatPassword = binding.repeatPasswordInput.text.toString().trim()

        val errors = mutableListOf<String>()
        if (username.isEmpty()) {
            errors.add(getString(R.string.empty_username))
        }

        if (password.isEmpty() || repeatPassword.isEmpty()) {
            errors.add(getString(R.string.password_and_or_repeat_empty))
        }

        if (password != repeatPassword) {
            errors.add(getString(R.string.password_not_match_repeat))
        }

        if (errors.isEmpty()) {
            viewModel.register(username, password, firstName, lastName)
        } else {
            binding.errorsTv.text = buildErrors(errors)
        }
    }

    private fun buildErrors(errors: List<String>): String {

        val builder = StringBuilder()
        errors.forEach {
            builder.append("*").append(it).append("\n")
        }

        return builder.toString()
    }

    private fun observerState() {
        viewModel.registerViewState.observe(viewLifecycleOwner) {
            if (it.error == null) {
                val intent = Intent(requireActivity(), HomeActivity::class.java)
                startActivity(intent)
                activity?.finish()
            } else {
                binding.errorsTv.text = buildErrors(listOf("${it.error}"))
            }
        }
    }

}