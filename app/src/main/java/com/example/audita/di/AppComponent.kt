package com.example.audita.di

import com.example.feature_authorization_screen.data.repository.AuthUserRepositoryImpl
import com.example.feature_authorization_screen.di.AuthorizationDeps
import com.example.feature_authorization_screen.domain.repository.AuthUserRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [DataModule::class,
    DomainModule::class])
interface AppComponent : AuthorizationDeps {

    override val auth: FirebaseAuth
    override val repo: AuthUserRepository

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
interface DomainModule{

    @Binds
    fun authUserRepositoryImplToAuthUserRepository(
        repository: AuthUserRepositoryImpl
    ): AuthUserRepository

}