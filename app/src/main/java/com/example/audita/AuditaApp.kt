@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.example.audita

import android.app.Application
import com.example.audita.di.AppComponent
import com.example.audita.di.DaggerAppComponent
import com.example.feature_authorization_screen.di.AuthorizationDepsStore

class AuditaApp : Application() {

    val appComponent: AppComponent by lazy{
        DaggerAppComponent.builder()
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        AuthorizationDepsStore.deps = appComponent
    }
}