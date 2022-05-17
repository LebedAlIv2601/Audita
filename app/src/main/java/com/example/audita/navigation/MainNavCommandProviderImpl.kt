package com.example.audita.navigation

import com.example.audita.R
import com.example.core.NavCommand
import com.example.feature_main_screen.navigation.MainNavCommandProvider
import javax.inject.Inject

class MainNavCommandProviderImpl @Inject constructor() : MainNavCommandProvider {
    override val toSearch: NavCommand = NavCommand(R.id.action_mainFragment_to_searchFragment)
}