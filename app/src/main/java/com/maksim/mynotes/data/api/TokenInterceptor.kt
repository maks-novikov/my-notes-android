package com.maksim.mynotes.data.api

import com.maksim.mynotes.domain.session.UserSession
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
    private val userSession: UserSession?
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()


        if (userSession != null) {
            val newRequestBuilder = request.newBuilder()
            newRequestBuilder.header("Authorization", "Bearer ${userSession.token}")
            request = newRequestBuilder.build()
        }

        return chain.proceed(request)
    }
}