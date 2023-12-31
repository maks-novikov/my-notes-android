package com.maksim.mynotes.di

import android.content.Context
import com.maksim.mynotes.data.DefaultAuthRepository
import com.maksim.mynotes.data.api.BaseUrlProvider
import com.maksim.mynotes.data.api.TokenInterceptor
import com.maksim.mynotes.data.api.auth.AuthApi
import com.maksim.mynotes.data.api.auth.AuthService
import com.maksim.mynotes.data.api.notes.NotesApi
import com.maksim.mynotes.data.api.notes.NotesService
import com.maksim.mynotes.data.note.DefaultNoteRepository
import com.maksim.mynotes.data.session.DefaultSessionStorage
import com.maksim.mynotes.domain.AuthRepository
import com.maksim.mynotes.domain.note.NoteMapper
import com.maksim.mynotes.domain.note.NoteRepository
import com.maksim.mynotes.domain.session.SessionHolder
import com.maksim.mynotes.domain.session.SessionStorage
import com.maksim.mynotes.domain.session.UserSession
import com.maksim.mynotes.domain.useCase.GetNotesUseCase
import com.maksim.mynotes.domain.useCase.LoginUseCase
import com.maksim.mynotes.domain.useCase.LogoutUseCase
import com.maksim.mynotes.domain.useCase.RegisterUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideNoteMapper(): NoteMapper {
        return NoteMapper()
    }

    @Provides
    fun provideUserSession(sessionStorage: SessionStorage): UserSession? {
        return sessionStorage.getSession()
    }

    @Provides
    fun provideTokenInterceptor(userSession: UserSession?): TokenInterceptor {
        return TokenInterceptor(userSession)
    }

    @Provides
    @Named("AuthorizedHttpClient")
    fun getAuthorizedHttpClient(tokenInterceptor: TokenInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(tokenInterceptor).build()
    }

    @Provides
    @Singleton
    fun provideAuthApi(): AuthApi {
        return Retrofit.Builder()
            .baseUrl(BaseUrlProvider.getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthService(authApi: AuthApi): AuthService {
        return AuthService(authApi, Dispatchers.IO)
    }

    @Provides
    @Singleton
    fun provideNotesApi(@Named("AuthorizedHttpClient") okHttpClient: OkHttpClient): NotesApi {
        return Retrofit.Builder()
            .baseUrl(BaseUrlProvider.getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(NotesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNotesService(notesApi: NotesApi): NotesService {
        return NotesService(notesApi, Dispatchers.IO)
    }

    @Provides
    @Singleton
    fun provideNoteRepository(notesService: NotesService, noteMapper: NoteMapper): NoteRepository {
        return DefaultNoteRepository(notesService, noteMapper)
    }

    @Provides
    @Singleton
    fun provideSessionStorage(@ApplicationContext appContext: Context): SessionStorage {
        return DefaultSessionStorage(appContext)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        authService: AuthService,
        sessionStorage: SessionStorage
    ): AuthRepository {
        return DefaultAuthRepository(authService, SessionHolder(sessionStorage))
    }

    @Provides
    fun provideRegisterUseCase(authRepository: AuthRepository): RegisterUseCase {
        return RegisterUseCase(authRepository)
    }

    @Provides
    fun provideLoginUseCase(authRepository: AuthRepository): LoginUseCase {
        return LoginUseCase(authRepository)
    }

    @Provides
    fun provideLogoutUseCase(authRepository: AuthRepository): LogoutUseCase {
        return LogoutUseCase(authRepository)
    }

    @Provides
    fun provideGetNotesUseCase(noteRepository: NoteRepository): GetNotesUseCase {
        return GetNotesUseCase(noteRepository)
    }
}