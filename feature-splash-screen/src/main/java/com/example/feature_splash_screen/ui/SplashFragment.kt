package com.example.feature_splash_screen.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.core.navigate
import com.example.feature_splash_screen.R
import com.example.feature_splash_screen.di.SplashComponentViewModel
import com.example.feature_splash_screen.navigation.SplashNavCommandProvider
import com.example.feature_splash_screen.ui.vm.SplashViewModel
import javax.inject.Inject

class SplashFragment : Fragment() {

    @Inject
    internal lateinit var splashNavCommandProvider: SplashNavCommandProvider

    private val vm: SplashViewModel by viewModels {
        vmFactory
    }

    @Inject
    internal lateinit var vmFactory: SplashViewModel.Factory

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get(SplashComponentViewModel::class.java)
            .splashComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (vm.getAuth()) {
            navigate(splashNavCommandProvider.toMain)
        } else {
            navigate(splashNavCommandProvider.toAuth)
        }
        super.onViewCreated(view, savedInstanceState)
    }
}