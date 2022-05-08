package com.example.audita.navigation

import com.example.audita.R
import com.example.core.NavCommand
import com.example.feature_splash_screen.navigation.SplashNavCommandProvider
import javax.inject.Inject

/**
 * Реализация интерфейса SplashNavCommandProvider из модуля
 * feature-splash-screen
 */

class SplashNavCommandProviderImpl @Inject constructor() : SplashNavCommandProvider{


    /**
     * Реализация команды перехода на LogOutFragment
     */
    override val toLogOut: NavCommand = NavCommand(R.id.action_splashFragment_to_logOutFragment)

    /**
     * Реализация команды перехода на MainFragment
     */
    override val toMain: NavCommand = NavCommand(R.id.action_splashFragment_to_mainFragment)

}