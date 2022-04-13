package com.example.feature_authorization_screen.domain.usecase

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.feature_authorization_screen.domain.model.UserForAuth
import com.example.feature_authorization_screen.domain.repository.AuthUserRepository
import com.example.feature_authorization_screen.utils.AuthorizationState
import javax.inject.Inject

class AuthUserUseCase @Inject constructor(private val repository: AuthUserRepository) {

    operator fun invoke(user: UserForAuth): LiveData<AuthorizationState<Boolean>>{
        Log.e("Ask", "asking for auth in usecase")
        return repository.authUser(user)
    }
}