package com.example.audita.di

import com.example.audita.navigation.AuthNavCommandProviderImpl
import com.example.audita.navigation.SplashNavCommandProviderImpl
import com.example.feature_authorization_screen.data.repository.AuthUserRepositoryImpl
import com.example.feature_authorization_screen.di.AuthorizationDeps
import com.example.feature_authorization_screen.domain.repository.AuthUserRepository
import com.example.feature_authorization_screen.navigation.AuthNavCommandProvider
import com.example.feature_splash_screen.data.GetUserAuthRepositoryImpl
import com.example.feature_splash_screen.di.SplashDeps
import com.example.feature_splash_screen.domain.repository.GetUserAuthRepository
import com.example.feature_splash_screen.navigation.SplashNavCommandProvider
import com.google.firebase.auth.FirebaseAuth
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [DataModule::class,
    DomainModule::class, NavigationModule::class])
interface AppComponent : AuthorizationDeps, SplashDeps {

    override val auth: FirebaseAuth
    override val repo: AuthUserRepository
    override val repoAuth: GetUserAuthRepository

    override val authNavCommandProvider: AuthNavCommandProvider
    override val splashNavCommandProvider: SplashNavCommandProvider

    @Component.Builder
    interface Builder{
        fun build(): AppComponent
    }
}

@Module
class DataModule{

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

}

@Module
interface NavigationModule{

    @Binds
    fun splashNavCommandProviderImplToInterface(
        provider: SplashNavCommandProviderImpl
    ): SplashNavCommandProvider

    @Binds
    fun authNavCommandProviderImplToInterface(
        provider: AuthNavCommandProviderImpl
    ): AuthNavCommandProvider

}

@Module
interface DomainModule{

    @Binds
    fun authUserRepositoryImplToAuthUserRepository(
        repository: AuthUserRepositoryImpl
    ): AuthUserRepository

    @Binds
    fun getAuthUserRepositoryImplToInterface(
        repository: GetUserAuthRepositoryImpl
    ): GetUserAuthRepository

}