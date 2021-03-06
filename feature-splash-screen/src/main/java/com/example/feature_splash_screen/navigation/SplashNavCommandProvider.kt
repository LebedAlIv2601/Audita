package com.example.feature_splash_screen.navigation

import com.example.core.NavCommand

interface SplashNavCommandProvider {

    val toLogOut: NavCommand
    val toMain: NavCommand
}