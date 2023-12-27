package com.maksim.mynotes.ui.di

import com.maksim.mynotes.domain.useCase.RegisterUseCase

interface AppContainer {
    fun provideRegisterUseCase(): RegisterUseCase

}