package com.example.feature_main_screen

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.core.navigate
import com.example.feature_main_screen.databinding.FragmentMainBinding
import com.example.feature_main_screen.di.MainComponentViewModel
import com.example.feature_main_screen.navigation.MainNavCommandProvider
import javax.inject.Inject

class MainFragment : Fragment() {

    private var binding: FragmentMainBinding? = null

    @Inject
    internal lateinit var mainNavCommandProvider: MainNavCommandProvider

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get(MainComponentViewModel::class.java)
            .mainComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainBinding.bind(inflater.inflate(R.layout.fragment_main, container, false))

        //navigate(mainNavCommandProvider.toSearch, R.id.fragment_container)
        navigate(mainNavCommandProvider.toSearch)
/*
        binding?.bottomNavigationView?.setOnItemReselectedListener {
            when(it.itemId){
                R.id.search_bottom_nav-> navigate(mainNavCommandProvider.toSearch, R.id.fragment_container)
                R.id.chat_bottom_nav-> navigate(mainNavCommandProvider.toSearch, R.id.fragment_container)
                R.id.profile_bottom_nav-> navigate(mainNavCommandProvider.toSearch, R.id.fragment_container)
            }
            true
        }
*/
        return binding?.root
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}