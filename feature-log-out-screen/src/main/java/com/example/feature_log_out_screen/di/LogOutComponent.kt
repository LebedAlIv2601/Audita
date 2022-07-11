package com.example.feature_log_out_screen.di

import androidx.lifecycle.ViewModel
import com.example.feature_log_out_screen.navigation.LogOutNavCommandProvider
import com.example.feature_log_out_screen.presentation.LogOutFragment
import com.google.firebase.auth.FirebaseAuth
import dagger.Component
import kotlin.properties.Delegates


@Component(dependencies = [LogOutDeps::class])
internal interface LogOutComponent {

    fun inject(fragment: LogOutFragment)

    @Component.Builder
    interface Builder{

        fun deps(deps: LogOutDeps): Builder

        fun build(): LogOutComponent
    }

}

interface LogOutDeps{

    val logOutNavCommandProvider: LogOutNavCommandProvider

}

interface LogOutDepsProvider{

    val deps: LogOutDeps

    companion object : LogOutDepsProvider by LogOutDepsStore

}

object LogOutDepsStore : LogOutDepsProvider{

    override var deps: LogOutDeps by Delegates.notNull()
}

internal class LogOutComponentViewModel: ViewModel(){

    val logOutComponent = DaggerLogOutComponent.builder()
        .deps(LogOutDepsProvider.deps).build()

}