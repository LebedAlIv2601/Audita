package com.example.feature_splash_screen.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.feature_splash_screen.navigation.SplashNavCommandProvider
import com.example.feature_splash_screen.ui.SplashFragment
import dagger.Component
import kotlin.properties.Delegates

@Component(dependencies = [SplashDeps::class])
internal interface SplashComponent {

    fun inject(fragment: SplashFragment)

    @Component.Builder
    interface Builder{

        fun deps(splashDeps: SplashDeps): Builder

        fun build(): SplashComponent
    }
}

interface SplashDeps{

    val splashNavCommandProvider: SplashNavCommandProvider

}

interface SplashDepsProvider{

    val deps: SplashDeps

    companion object :SplashDepsProvider by SplashDepsStore

}

object SplashDepsStore : SplashDepsProvider{

    override var deps: SplashDeps by Delegates.notNull()
}

internal class SplashComponentViewModel: ViewModel(){

    val splashComponent = DaggerSplashComponent.builder()
        .deps(SplashDepsProvider.deps).build()

}