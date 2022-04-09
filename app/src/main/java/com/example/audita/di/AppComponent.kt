package com.example.audita.di

import dagger.Component

@Component
class AppComponent {

    @Component.Builder
    interface Builder{
        fun build(): AppComponent
    }
}