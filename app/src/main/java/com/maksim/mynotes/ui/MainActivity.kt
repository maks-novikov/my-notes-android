package com.maksim.mynotes.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.maksim.mynotes.R
import com.maksim.mynotes.data.api.auth.AuthService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}