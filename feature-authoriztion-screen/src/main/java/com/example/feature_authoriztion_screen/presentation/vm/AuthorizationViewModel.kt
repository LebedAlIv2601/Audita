package com.example.feature_authoriztion_screen.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.feature_authoriztion_screen.domain.model.UserForAuth
import com.example.feature_authoriztion_screen.domain.usecase.AuthUserUseCase
import com.example.feature_authoriztion_screen.domain.usecase.IsUserAuthUseCase
import com.example.feature_authoriztion_screen.utils.Resource

class AuthorizationViewModel (private val authUserUseCase: AuthUserUseCase,
                              private val isUserAuthUseCase: IsUserAuthUseCase) : ViewModel() {

    private val _isAuth = MutableLiveData<Boolean>()
    val isAuth: LiveData<Boolean>
        get() = _isAuth

    fun checkUserAuth(){
        _isAuth.value = isUserAuthUseCase()
    }

    fun authUser(user: UserForAuth): Resource<Boolean>?{
        return authUserUseCase(user).value
    }

}