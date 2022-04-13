package com.example.feature_authorization_screen.data

import com.example.feature_authorization_screen.data.model.DataUserForAuth
import com.example.feature_authorization_screen.domain.model.UserForAuth

fun UserForAuth.toData(): DataUserForAuth {
    return DataUserForAuth(
        email = this.email,
        password = this.password
    )
}