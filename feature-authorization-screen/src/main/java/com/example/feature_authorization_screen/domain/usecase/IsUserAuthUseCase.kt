package com.example.feature_authorization_screen.domain.usecase

import com.example.feature_authorization_screen.domain.repository.AuthUserRepository
import javax.inject.Inject

class IsUserAuthUseCase @Inject constructor(private val repository: AuthUserRepository) {

    operator fun invoke(): Boolean{
        return repository.isUserAuthenticated()
    }
}