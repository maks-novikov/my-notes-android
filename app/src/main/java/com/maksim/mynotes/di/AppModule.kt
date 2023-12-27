package com.maksim.mynotes.di

import android.content.Context
import com.maksim.mynotes.data.DefaultAuthRepository
import com.maksim.mynotes.data.api.auth.AuthApi
import com.maksim.mynotes.data.api.auth.AuthService
import com.maksim.mynotes.data.session.DefaultSessionStorage
import com.maksim.mynotes.domain.AuthRepository
import com.maksim.mynotes.domain.session.SessionHolder
import com.maksim.mynotes.domain.session.SessionStorage
import com.maksim.mynotes.domain.session.UserSession
import com.maksim.mynotes.domain.useCase.RegisterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAuthApi(): AuthApi {
        return Retrofit.Builder()
            .baseUrl("http://192.168.0.115:8080/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthService(authApi: AuthApi): AuthService {
        return AuthService(authApi)
    }

    @Provides
    @Singleton
    fun provideSessionStorage(@ApplicationContext appContext: Context): SessionStorage {
        return DefaultSessionStorage(appContext)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(authService: AuthService, sessionStorage: SessionStorage): AuthRepository {
        return DefaultAuthRepository(authService, SessionHolder(sessionStorage))
    }

    @Provides
    fun provideRegisterUseCase(authRepository: AuthRepository): RegisterUseCase {
        return RegisterUseCase(authRepository)
    }

    @Provides
    fun getSession(sessionStorage: SessionStorage): UserSession? {
        return sessionStorage.getSession()
    }
}