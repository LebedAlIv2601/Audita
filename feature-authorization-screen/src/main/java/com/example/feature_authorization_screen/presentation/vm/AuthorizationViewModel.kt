package com.example.feature_authorization_screen.presentation.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.feature_authorization_screen.domain.model.UserForAuth
import com.example.feature_authorization_screen.domain.usecase.AuthUserUseCase
import com.example.feature_authorization_screen.domain.usecase.IsUserAuthUseCase
import com.example.feature_authorization_screen.domain.usecase.RegUserUseCase
import com.example.feature_authorization_screen.utils.AuthScreenState
import com.example.feature_authorization_screen.utils.AuthorizationState

class AuthorizationViewModel (private val authUserUseCase: AuthUserUseCase,
                              private val regUseCase: RegUserUseCase,
                              private val isUserAuthUseCase: IsUserAuthUseCase) : ViewModel() {


    private val _authScreenState = MutableLiveData<AuthScreenState>()
    val authScreenState: LiveData<AuthScreenState>
        get() = _authScreenState

    init {
        _authScreenState.value = AuthScreenState.SIGN_IN
    }

    fun changeAuthScreenState(){
        _authScreenState.value = if(_authScreenState.value == AuthScreenState.SIGN_IN)
            AuthScreenState.SIGN_UP else AuthScreenState.SIGN_IN
    }

    fun checkUserAuth(): Boolean{
        return isUserAuthUseCase()
    }

    fun authUser(user: UserForAuth)= authUserUseCase(user)

    fun regUser(user: UserForAuth) = regUseCase(user)

}