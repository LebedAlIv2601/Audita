package com.example.audita.navigation

import com.example.audita.R
import com.example.core.NavCommand
import com.example.feature_authorization_screen.navigation.AuthNavCommandProvider
import javax.inject.Inject

/**
 * Реализация интерфейса AuthNavCommandProvider из модуля
 * feature-authorization-screen
 */

class AuthNavCommandProviderImpl @Inject constructor() : AuthNavCommandProvider {

    /**
     * Реализация команды перехода на MainFragment
     */
    override val toMain: NavCommand = NavCommand(R.id.action_authorizationFragment_to_mainFragment)
}