package com.example.feature_authorization_screen.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.feature_authorization_screen.data.model.DataUserForAuth
import com.example.feature_authorization_screen.utils.AuthorizationState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import javax.inject.Inject

class FirebaseAuthHelper @Inject constructor(private val auth: FirebaseAuth){

    fun isUserAuthenticatedInFirebase() = auth.currentUser != null

    fun registrOrAuthUser(user: DataUserForAuth): LiveData<AuthorizationState<Boolean>> =
        liveData(Dispatchers.IO){
        Log.e("AuthoriztionData", "Loading")
        emit(AuthorizationState.Loading(data = null))
        try {
            val isAuth = auth.createUserWithEmailAndPassword(user.email, user.password)
            emit(AuthorizationState.Success(data = isAuth.isSuccessful))
        } catch (e: Exception){
            emit(AuthorizationState.Error(data = null, message = "Authentication failed!"))
        }
    }

}