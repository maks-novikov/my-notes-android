package com.maksim.mynotes.ui.auth.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.maksim.mynotes.R
import com.maksim.mynotes.databinding.FragmentLoginBinding
import com.maksim.mynotes.ui.MainActivity
import com.maksim.mynotes.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import java.lang.StringBuilder

@AndroidEntryPoint
class LoginFragment : BaseFragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModels<LoginFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.submitBtn.setOnClickListener {
            submitLogin()
        }
    }

    private fun submitLogin() {

        binding.errorsTv.text = ""

        val username = binding.usernameInput.text.toString().trim()
        val password = binding.passwordInput.text.toString().trim()

        val errors = mutableListOf<String>()

        if (username.isEmpty()) {
            errors.add(getString(R.string.empty_username))
        }

        if (password.isEmpty()) {
            errors.add(getString(R.string.empty_password))
        }

        if (errors.isEmpty()) {
            viewModel.login(username, password)
        } else {
            binding.errorsTv.text = buildErrors(errors)
        }

        observerState()

    }

    private fun observerState() {
        viewModel.loginViewState.observe(viewLifecycleOwner) {
            if (it.error == null) {
                val intent = Intent(requireActivity(), MainActivity::class.java)
                startActivity(intent)
                activity?.finish()
            } else {
                buildErrors(listOf("${it.error}"))
            }
        }
    }

    private fun buildErrors(errors: List<String>): String {

        val builder = StringBuilder()
        errors.forEach {
            builder.append("*").append(it).append("\n")
        }

        return builder.toString()
    }

}