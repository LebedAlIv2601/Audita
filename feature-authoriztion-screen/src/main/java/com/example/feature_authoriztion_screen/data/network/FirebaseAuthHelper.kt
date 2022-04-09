package com.example.feature_authoriztion_screen.data.network

import android.content.Context
import androidx.lifecycle.liveData
import com.example.feature_authoriztion_screen.data.model.DataUserForAuth
import com.example.feature_authoriztion_screen.utils.Resource
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class FirebaseAuthHelper (private val auth: FirebaseAuth){

    fun isUserAuthenticatedInFirebase() = auth.currentUser != null

    fun registrOrAuthUser(user: DataUserForAuth) = liveData(Dispatchers.IO){
        emit(Resource.Loading(data = null))
        try {
            val isAuth = auth.createUserWithEmailAndPassword(user.email, user.password)
            emit(Resource.Success(data = isAuth.isSuccessful))
        } catch (e: Exception){
            emit(Resource.Error(data = null, message = "Authentication failed!"))
        }
    }

}