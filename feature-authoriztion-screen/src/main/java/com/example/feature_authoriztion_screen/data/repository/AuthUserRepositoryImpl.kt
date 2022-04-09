package com.example.feature_authoriztion_screen.data.repository

import androidx.lifecycle.LiveData
import com.example.feature_authoriztion_screen.data.network.FirebaseAuthHelper
import com.example.feature_authoriztion_screen.data.toData
import com.example.feature_authoriztion_screen.domain.model.UserForAuth
import com.example.feature_authoriztion_screen.domain.repository.AuthUserRepository
import com.example.feature_authoriztion_screen.utils.Resource

class AuthUserRepositoryImpl(private val helper: FirebaseAuthHelper) : AuthUserRepository {
    override fun regOrAuthUser(user: UserForAuth): LiveData<Resource<Boolean>> {
        return helper.registrOrAuthUser(user.toData())
    }

    override fun isUserAuthenticated(): Boolean {
        return helper.isUserAuthenticatedInFirebase()
    }
}