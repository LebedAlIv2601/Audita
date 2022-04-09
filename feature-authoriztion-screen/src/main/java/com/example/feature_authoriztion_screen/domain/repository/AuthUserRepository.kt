package com.example.feature_authoriztion_screen.domain.repository

import androidx.lifecycle.LiveData
import com.example.feature_authoriztion_screen.domain.model.UserForAuth
import com.example.feature_authoriztion_screen.utils.Resource

interface AuthUserRepository{

    fun regOrAuthUser(user: UserForAuth): LiveData<Resource<Boolean>>

    fun isUserAuthenticated(): Boolean

}