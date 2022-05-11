package com.example.feature_main_screen.di

import androidx.lifecycle.ViewModel
import com.example.feature_main_screen.MainFragment
import com.example.feature_main_screen.navigation.MainNavCommandProvider
import dagger.Component
import kotlin.properties.Delegates

@Component(dependencies = [MainDeps::class])
internal interface MainComponent {

    fun inject(fragment: MainFragment)


    @Component.Builder
    interface Builder{

        fun deps(mainDeps: MainDeps): Builder

        fun build(): MainComponent
    }

}

interface MainDeps{
    val mainNavCommandProvider: MainNavCommandProvider
}

interface MainDepsProvider{

    val deps: MainDeps

    companion object : MainDepsProvider by MainDepsStore

}

object MainDepsStore : MainDepsProvider{

    override var deps: MainDeps by Delegates.notNull()
}

internal class MainComponentViewModel: ViewModel(){

    val mainComponent = DaggerMainComponent.builder()
        .deps(MainDepsProvider.deps).build()

}