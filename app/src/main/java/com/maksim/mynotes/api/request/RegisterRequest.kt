package com.maksim.mynotes.api.request

import com.maksim.mynotes.data.UserRole

data class RegisterRequest(
    val login: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val role: UserRole,
    val isActive: Boolean
)
