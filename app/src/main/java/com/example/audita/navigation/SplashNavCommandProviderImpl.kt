package com.example.audita.navigation

import com.example.audita.R
import com.example.core.NavCommand
import com.example.feature_splash_screen.navigation.SplashNavCommandProvider
import javax.inject.Inject

class SplashNavCommandProviderImpl @Inject constructor() : SplashNavCommandProvider{

    override val toAuth: NavCommand = NavCommand(R.id.action_splashFragment_to_authorizationFragment)
    override val toMain: NavCommand = NavCommand(R.id.action_splashFragment_to_mainFragment)

}