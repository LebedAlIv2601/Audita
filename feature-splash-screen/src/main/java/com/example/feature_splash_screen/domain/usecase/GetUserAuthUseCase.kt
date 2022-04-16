package com.example.feature_splash_screen.domain.usecase

import com.example.feature_splash_screen.domain.repository.GetUserAuthRepository
import javax.inject.Inject

class GetUserAuthUseCase @Inject constructor(private val repository: GetUserAuthRepository) {

    fun getUserAuth(): Boolean = repository.getUserAuth()
}