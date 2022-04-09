package com.example.feature_authoriztion_screen.domain.usecase

import androidx.lifecycle.LiveData
import com.example.feature_authoriztion_screen.domain.model.UserForAuth
import com.example.feature_authoriztion_screen.domain.repository.AuthUserRepository
import com.example.feature_authoriztion_screen.utils.Resource

class AuthUserUseCase(private val repository: AuthUserRepository) {

    operator fun invoke(user: UserForAuth): LiveData<Resource<Boolean>>{
        return repository.regOrAuthUser(user)
    }
}