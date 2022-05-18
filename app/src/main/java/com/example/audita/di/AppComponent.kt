package com.example.audita.di

import com.example.audita.navigation.AuthNavCommandProviderImpl
import com.example.audita.navigation.LogOutNavCommandProviderImpl
import com.example.audita.navigation.MainNavCommandProviderImpl
import com.example.audita.navigation.SplashNavCommandProviderImpl
import com.example.feature_authorization_screen.data.repository.AuthUserRepositoryImpl
import com.example.feature_authorization_screen.di.AuthorizationDeps
import com.example.feature_authorization_screen.domain.repository.AuthUserRepository
import com.example.feature_authorization_screen.navigation.AuthNavCommandProvider
import com.example.feature_log_out_screen.di.LogOutDeps
import com.example.feature_log_out_screen.navigation.LogOutNavCommandProvider
import com.example.feature_main_screen.di.MainDeps
import com.example.feature_main_screen.navigation.MainNavCommandProvider
import com.example.feature_splash_screen.data.GetUserAuthRepositoryImpl
import com.example.feature_splash_screen.di.SplashDeps
import com.example.feature_splash_screen.domain.repository.GetUserAuthRepository
import com.example.feature_splash_screen.navigation.SplashNavCommandProvider
import com.google.firebase.auth.FirebaseAuth
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides

/**
 * Главный Dagger компонент приложения.
 * Служит для предоставления зависимостей всем модулям приложения.
 */
@Component(modules = [DataModule::class,
    DomainModule::class, NavigationModule::class])
interface AppComponent : AuthorizationDeps, SplashDeps, LogOutDeps, MainDeps {

    /**
     * Поле для экземпляра FirebaseAuth
     */
    override val auth: FirebaseAuth
    /**
     * Поле для экземпляра AuthUserRepository
     */
    override val repo: AuthUserRepository
    /**
     * Поле для экземпляра GetUserAuthRepository
     */
    override val repoAuth: GetUserAuthRepository

    /**
     * Поле для экземпляра AuthNavCommandProvider
     */
    override val authNavCommandProvider: AuthNavCommandProvider
    /**
     * Поле для экземпляра SplashNavCommandProvider
     */
    override val splashNavCommandProvider: SplashNavCommandProvider

    override val logOutNavCommandProvider: LogOutNavCommandProvider

    /**
     * Builder для AppComponent
     */
    @Component.Builder
    interface Builder{
        /**
         * Функция для создания AppComponent
         */
        fun build(): AppComponent
    }
}

/**
 * Dagger модуль для предоставления зависимостей в data-слое модулей приложения.
 */
@Module
class DataModule{

    /**
     * Предоставление экземпляра FirebaseAuth
     * @return FirebaseAuth: экземпляр FirebaseAuth для работы с авторизацией пользователя
     * в Firebase.
     */
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

}

/**
 * Dagger модуль для предоставления зависимостей в data-слое модулей приложения.
 */
@Module
interface NavigationModule{

    /**
     * Функция для получения экземпляра интерфейса SplashNavCommandProvider
     * @param provider: реализация SplashNavCommandProvider
     * @return SplashNavCommandProvider: экземпляр интерфейса SplashNavCommandProvider
     */
    @Binds
    fun splashNavCommandProviderImplToInterface(
        provider: SplashNavCommandProviderImpl
    ): SplashNavCommandProvider

    /**
     * Функция для получения экземпляра интерфейса AuthNavCommandProvider
     * @param provider: реализация AuthNavCommandProvider
     * @return SplashNavCommandProvider: экземпляр интерфейса AuthNavCommandProvider
     */
    @Binds
    fun authNavCommandProviderImplToInterface(
        provider: AuthNavCommandProviderImpl
    ): AuthNavCommandProvider


    @Binds
    fun logOutNavCommandImplToInterface(
        provider: LogOutNavCommandProviderImpl
    ): LogOutNavCommandProvider

    @Binds
    fun mainNavCommandImplToInterface(
        provider: MainNavCommandProviderImpl
    ): MainNavCommandProvider

}

/**
 * Dagger модуль для предоставления зависимостей в domain-слое модулей приложения.
 */
@Module
interface DomainModule{

    /**
     * Функция для получения экземпляра интерфейса AuthUserRepository
     * @param repository: реализация AuthUserRepository
     * @return AuthUserRepository: экземпляр интерфейса AuthUserRepository
     */
    @Binds
    fun authUserRepositoryImplToAuthUserRepository(
        repository: AuthUserRepositoryImpl
    ): AuthUserRepository

    /**
     * Функция для получения экземпляра интерфейса GetUserAuthRepository
     * @param repository: реализация GetUserAuthRepository
     * @return GetUserAuthRepository: экземпляр интерфейса GetUserAuthRepository
     */
    @Binds
    fun getAuthUserRepositoryImplToInterface(
        repository: GetUserAuthRepositoryImpl
    ): GetUserAuthRepository

}