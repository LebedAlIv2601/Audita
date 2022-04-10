package com.example.feature_authorization_screen.domain.model

data class UserForAuth(
    val nickname: String,
    val email: String,
    val password: String
)
