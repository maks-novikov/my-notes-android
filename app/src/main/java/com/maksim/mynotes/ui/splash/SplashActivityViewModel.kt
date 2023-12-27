package com.maksim.mynotes.ui.splash

import androidx.lifecycle.ViewModel
import com.maksim.mynotes.domain.session.UserSession
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashActivityViewModel @Inject constructor(val session: UserSession?): ViewModel()