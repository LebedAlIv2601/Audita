package com.example.feature_authoriztion_screen.domain.usecase

import com.example.feature_authoriztion_screen.domain.repository.AuthUserRepository

class IsUserAuthUseCase(private val repository: AuthUserRepository) {

    operator fun invoke(): Boolean{
        return repository.isUserAuthenticated()
    }
}