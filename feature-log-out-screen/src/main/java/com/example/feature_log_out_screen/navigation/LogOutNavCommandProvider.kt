package com.example.feature_log_out_screen.navigation

import com.example.core.NavCommand

interface LogOutNavCommandProvider {

    val toAuthWithRegState: NavCommand

    val toAuthWithAuthState: NavCommand

}