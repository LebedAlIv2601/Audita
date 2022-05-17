package com.example.audita.navigation

import android.os.Bundle
import com.example.audita.R
import com.example.core.Constants
import com.example.core.NavCommand
import com.example.feature_log_out_screen.navigation.LogOutNavCommandProvider
import javax.inject.Inject

class LogOutNavCommandProviderImpl @Inject constructor(): LogOutNavCommandProvider {

    private val bundleReg = Bundle()
    private val bundleAuth = Bundle()
    init {
        bundleReg.putInt("state", Constants.AUTH_SCREEN_REG_STATE)
        bundleAuth.putInt("state", Constants.AUTH_SCREEN_AUTH_STATE)
    }
    override val toAuthWithRegState: NavCommand =
        NavCommand(R.id.action_logOutFragment_to_authorizationFragment, bundleReg)
    override val toAuthWithAuthState: NavCommand =
        NavCommand(R.id.action_logOutFragment_to_authorizationFragment, bundleAuth)
}