package com.maksim.mynotes.ui.auth.register

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.maksim.mynotes.R
import com.maksim.mynotes.api.request.RegisterRequest
import com.maksim.mynotes.data.UserRole
import com.maksim.mynotes.data.auth.AuthService
import com.maksim.mynotes.databinding.FragmentRegisterBinding
import com.maksim.mynotes.ui.base.BaseFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterFragment : BaseFragment() {

    private lateinit var binding: FragmentRegisterBinding

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


    }

    private fun submitRegister() {


        val auth = AuthService()

        CoroutineScope(Dispatchers.Main).launch {

            auth.register(
                RegisterRequest(
                    "maks",
                    "max123",
                    "maksim",
                    "novikov",
                    UserRole.CLIENT,
                    true
                )
            )
            /* auth.login(LoginRequest("maks@g.com", "max123")) */
        }

        val username = binding.usernameInput.text.toString().trim()
        val firstName = binding.firstNameInput.text.toString().trim()
        val lastName = binding.lastNameInput.text.toString().trim()
        val password = binding.passwordInput.text.toString().trim()
        val repeatPassword = binding.repeatPasswordInput.text.toString().trim()

        val errors = mutableListOf<String>()
        if (username.isNullOrEmpty()) {
            errors.add(getString(R.string.empty_username))
        }

        if (password.isNullOrEmpty() || repeatPassword.isNullOrEmpty()) {
            errors.add(getString(R.string.password_and_or_repeat_empty))
        }

        if (password != repeatPassword) {
            errors.add(getString(R.string.password_not_match_repeat))
        }


        if (errors.isEmpty()) {

        } else {

        }
    }

}