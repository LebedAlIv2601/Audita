package com.example.audita.navigation

import com.example.audita.R
import com.example.core.NavCommand
import com.example.feature_authorization_screen.navigation.AuthNavCommandProvider
import javax.inject.Inject

class AuthNavCommandProviderImpl @Inject constructor() : AuthNavCommandProvider {
    override val toMain: NavCommand = NavCommand(R.id.action_authorizationFragment_to_mainFragment)
}