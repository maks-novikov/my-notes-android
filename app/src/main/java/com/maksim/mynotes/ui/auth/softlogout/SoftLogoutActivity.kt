package com.maksim.mynotes.ui.auth.softlogout

import android.os.Bundle
import com.maksim.mynotes.databinding.ActivitySoftLogoutBinding
import com.maksim.mynotes.ui.base.BaseActivity

class SoftLogoutActivity : BaseActivity() {

    private lateinit var binding: ActivitySoftLogoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySoftLogoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginAgain.setOnClickListener {

        }

        binding.logout.setOnClickListener {


        }
    }
}