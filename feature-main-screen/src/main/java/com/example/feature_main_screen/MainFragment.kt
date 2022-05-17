package com.example.feature_main_screen

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
//import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.setupWithNavController
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

        //val navController = findNavController(requireActivity(), R.id.nav_host_fragment)
        val navController = findNavController()

        binding?.bottomNavigationView?.setupWithNavController(navController)

        //navigate(mainNavCommandProvider.toSearch, R.id.nav_host_fragment)
        //navigate(mainNavCommandProvider.toSearch)

        //navigate(NavCommand(R.id.action_placeholder_to_search_bottom_nav))

        /*binding?.bottomNavigationView?.setOnItemReselectedListener {
            when(it.itemId){
                R.id.search_bottom_nav-> navigate(NavCommand(R.id.action_placeholder_to_search_bottom_nav))
                R.id.chat_bottom_nav-> navigate(NavCommand(R.id.action_placeholder_to_chat_bottom_nav))
                R.id.profile_bottom_nav-> navigate(NavCommand(R.id.action_placeholder_to_profile_bottom_nav))
            }
            true
        }*/

        return binding?.root
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}