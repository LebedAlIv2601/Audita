package com.example.feature_authorization_screen.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.feature_authorization_screen.domain.usecase.AuthUserUseCase
import com.example.feature_authorization_screen.domain.usecase.IsUserAuthUseCase
import javax.inject.Inject

class AuthorizationViewModelFactory @Inject constructor(
    private val authUseCase: AuthUserUseCase,
    private val isUserAuthUseCase: IsUserAuthUseCase)
    : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        require(modelClass == AuthorizationViewModel::class.java)
        return AuthorizationViewModel(authUseCase, isUserAuthUseCase) as T
    }

}