package com.example.feature_authorization_screen.domain.repository

import androidx.lifecycle.LiveData
import com.example.feature_authorization_screen.domain.model.UserForAuth
import com.example.feature_authorization_screen.utils.AuthorizationState

interface AuthUserRepository{

    fun regUser(user: UserForAuth): LiveData<AuthorizationState<Boolean>>

    fun authUser(user: UserForAuth): LiveData<AuthorizationState<Boolean>>

    fun isUserAuthenticated(): Boolean

}