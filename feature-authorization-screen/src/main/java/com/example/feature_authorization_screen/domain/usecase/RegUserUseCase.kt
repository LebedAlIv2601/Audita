package com.example.feature_authorization_screen.domain.usecase

import androidx.lifecycle.LiveData
import com.example.feature_authorization_screen.domain.model.UserForAuth
import com.example.feature_authorization_screen.domain.repository.AuthUserRepository
import com.example.feature_authorization_screen.utils.AuthorizationState
import javax.inject.Inject

class RegUserUseCase @Inject constructor(private val repository: AuthUserRepository) {

    operator fun invoke(user: UserForAuth): LiveData<AuthorizationState<Boolean>>{
        return repository.regUser(user)
    }
}