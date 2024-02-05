package com.maksim.mynotes.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.maksim.mynotes.databinding.ActivitySplashBinding
import com.maksim.mynotes.ui.auth.AuthActivity
import com.maksim.mynotes.ui.base.BaseActivity
import com.maksim.mynotes.ui.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : BaseActivity() {

    private lateinit var binding: ActivitySplashBinding

    private val viewModel by viewModels<SplashActivityViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (viewModel.session != null) {
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.syncNotes()
                startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
                finish()
            }
        } else {
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
        }
    }
}