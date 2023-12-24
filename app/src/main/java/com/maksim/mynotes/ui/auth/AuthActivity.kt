package com.maksim.mynotes.ui.auth

import android.os.Bundle

import com.maksim.mynotes.databinding.ActivityAuthBinding
import com.maksim.mynotes.ui.base.BaseActivity

class AuthActivity : BaseActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}