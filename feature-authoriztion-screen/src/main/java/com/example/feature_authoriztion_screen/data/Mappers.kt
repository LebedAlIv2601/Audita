package com.example.feature_authoriztion_screen.data

import com.example.feature_authoriztion_screen.data.model.DataUserForAuth
import com.example.feature_authoriztion_screen.domain.model.UserForAuth

fun UserForAuth.toData(): DataUserForAuth {
    return DataUserForAuth(
        email = this.email,
        password = this.password
    )
}