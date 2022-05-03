@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.example.audita

import android.app.Application
import com.example.audita.di.AppComponent
import com.example.audita.di.DaggerAppComponent
import com.example.feature_authorization_screen.di.AuthorizationDepsStore
import com.example.feature_splash_screen.di.SplashDepsStore

/**
 * Класс приложения. В нем происходит создание
 * Dagger компонента и присвоение его экземпляра
 * интерфейсам зависимостей из других модулей.
 */

class AuditaApp : Application() {

    /**
     * Даггер компонент с отложенным созданием.
     */
    val appComponent: AppComponent by lazy{
        DaggerAppComponent.builder()
            .build()
    }

    /**
     *  Наряду со стандратным методом OnCreate()
     *  функция присваивает экземпляр главного Dagger
     *  компонента интерфейсам зависимостей из других модулей.
     */
    override fun onCreate() {
        super.onCreate()
        AuthorizationDepsStore.deps = appComponent
        SplashDepsStore.deps = appComponent
    }
}