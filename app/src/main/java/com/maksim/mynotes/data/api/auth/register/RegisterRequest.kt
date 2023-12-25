package com.maksim.mynotes.data.api.auth.register

import com.maksim.mynotes.data.model.UserRole

data class RegisterRequest(
    val username: String,
    val password: String,
    val fistName: String?,
    val lastName: String?,
    val role: UserRole = UserRole.CLIENT,
    val isActive: Boolean = true
)
