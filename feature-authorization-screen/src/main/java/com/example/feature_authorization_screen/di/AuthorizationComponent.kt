package com.example.feature_authorization_screen.di

import androidx.lifecycle.ViewModel
import com.example.feature_authorization_screen.domain.repository.AuthUserRepository
import com.example.feature_authorization_screen.presentation.fragment.AuthorizationFragment
import com.google.firebase.auth.FirebaseAuth
import dagger.Component
import kotlin.properties.Delegates


@Component(dependencies = [AuthorizationDeps::class])
internal interface AuthorizationComponent {

    fun inject(fragment: AuthorizationFragment)


    @Component.Builder
    interface Builder{

        fun deps(authorizationDeps: AuthorizationDeps): Builder

        fun build(): AuthorizationComponent
    }

}

interface AuthorizationDeps{

    val auth: FirebaseAuth

    val repo: AuthUserRepository

}

interface AuthorizationDepsProvider{

    val deps: AuthorizationDeps

    companion object : AuthorizationDepsProvider by AuthorizationDepsStore

}

object AuthorizationDepsStore : AuthorizationDepsProvider{

    override var deps: AuthorizationDeps by Delegates.notNull()
}

internal class AuthorizationComponentViewModel: ViewModel(){

    val authorizationComponent = DaggerAuthorizationComponent.builder()
        .deps(AuthorizationDepsProvider.deps).build()

}