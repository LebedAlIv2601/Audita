package com.example.feature_authorization_screen.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.feature_authorization_screen.data.network.FirebaseAuthHelper
import com.example.feature_authorization_screen.data.toData
import com.example.feature_authorization_screen.domain.model.UserForAuth
import com.example.feature_authorization_screen.domain.repository.AuthUserRepository
import com.example.feature_authorization_screen.utils.AuthorizationState
import javax.inject.Inject

class AuthUserRepositoryImpl @Inject constructor(private val helper: FirebaseAuthHelper)
    : AuthUserRepository {


    override fun regOrAuthUser(user: UserForAuth): LiveData<AuthorizationState<Boolean>> {
        Log.e("Ask", "asking for auth in repository")
        return helper.registrOrAuthUser(user.toData())
    }

    override fun isUserAuthenticated(): Boolean {
        return helper.isUserAuthenticatedInFirebase()
    }
}