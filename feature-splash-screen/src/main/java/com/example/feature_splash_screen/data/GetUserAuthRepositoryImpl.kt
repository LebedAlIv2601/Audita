package com.example.feature_splash_screen.data

import com.example.feature_splash_screen.domain.repository.GetUserAuthRepository
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class GetUserAuthRepositoryImpl @Inject constructor(private val auth: FirebaseAuth) : GetUserAuthRepository {

    override fun getUserAuth(): Boolean = auth.currentUser != null


}