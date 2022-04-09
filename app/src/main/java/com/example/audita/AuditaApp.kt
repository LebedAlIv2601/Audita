package com.example.audita

import android.app.Application
import com.example.audita.di.AppComponent

class AuditaApp : Application() {

    val appComponent: AppComponent by lazy{

    }

    override fun onCreate() {
        super.onCreate()
        appComponent =
    }
}