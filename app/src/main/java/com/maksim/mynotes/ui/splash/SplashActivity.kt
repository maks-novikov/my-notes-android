package com.maksim.mynotes.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.maksim.mynotes.databinding.ActivitySplashBinding
import com.maksim.mynotes.ui.MainActivity
import com.maksim.mynotes.ui.auth.AuthActivity
import com.maksim.mynotes.ui.base.BaseActivity
import com.maksim.mynotes.ui.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity() {

    private lateinit var binding: ActivitySplashBinding

    private val viewModel by viewModels<SplashActivityViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = if (viewModel.session != null) {
            Intent(this, HomeActivity::class.java)
        } else {
            Intent(this, AuthActivity::class.java)
        }

        startActivity(intent)
        finish()
    }

}