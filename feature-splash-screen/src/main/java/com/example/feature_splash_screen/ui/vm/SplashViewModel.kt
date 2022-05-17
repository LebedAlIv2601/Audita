package com.example.feature_splash_screen.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.feature_splash_screen.domain.usecase.GetUserAuthUseCase
import com.example.feature_splash_screen.navigation.SplashNavCommandProvider
import javax.inject.Inject

class SplashViewModel(private val getUserAuthUseCase: GetUserAuthUseCase): ViewModel() {

    fun getAuth(): Boolean = getUserAuthUseCase.getUserAuth()

    class Factory @Inject constructor(private val useCase: GetUserAuthUseCase):
        ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            require(modelClass == SplashViewModel::class.java)
            return SplashViewModel(useCase) as T
        }
    }
}