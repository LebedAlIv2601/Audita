package com.example.feature_authorization_screen.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.feature_authorization_screen.data.model.DataUserForAuth
import com.example.feature_authorization_screen.utils.AuthorizationState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import javax.inject.Inject

class FirebaseAuthHelper @Inject constructor(private val auth: FirebaseAuth){

    fun isUserAuthenticatedInFirebase() = auth.currentUser != null

    fun regUser(user: DataUserForAuth): LiveData<AuthorizationState<Boolean>> =
        liveData(Dispatchers.IO){

        Log.e("AuthoriztionData", "Loading")
        emit(AuthorizationState.Loading(data = null))
        try {
            val isAuth = auth.createUserWithEmailAndPassword(user.email, user.password).await()
            Log.e("isAuth", "$isAuth")
            emit(AuthorizationState.Success(data = true))
        } catch (e: Exception){
            e.message?.let { Log.e("isAuthError", it) }
            emit(AuthorizationState.Error(data = null, message = e.message))
        }

    }

    fun authUser(user: DataUserForAuth): LiveData<AuthorizationState<Boolean>> =
        liveData(Dispatchers.IO){

            Log.e("AuthoriztionData", "Loading")
            emit(AuthorizationState.Loading(data = null))
            try {
                val isAuth = auth.signInWithEmailAndPassword(user.email, user.password).await()
                Log.e("isAuth", "$isAuth")
                emit(AuthorizationState.Success(data = true))
            } catch (e: Exception){
                e.message?.let { Log.e("isAuthError", it) }
                emit(AuthorizationState.Error(data = null, message = e.message))
            }

        }

}