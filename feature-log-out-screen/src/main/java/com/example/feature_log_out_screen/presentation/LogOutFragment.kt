package com.example.feature_log_out_screen.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import com.example.core.navigate
import com.example.feature_log_out_screen.R
import com.example.feature_log_out_screen.databinding.FragmentLogOutBinding
import com.example.feature_log_out_screen.di.LogOutComponentViewModel
import com.example.feature_log_out_screen.navigation.LogOutNavCommandProvider
import javax.inject.Inject


class LogOutFragment : Fragment() {

    private var binding: FragmentLogOutBinding? = null

    @Inject
    internal lateinit var navCommandProvider: LogOutNavCommandProvider

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get(LogOutComponentViewModel::class.java)
            .logOutComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        activity?.window?.statusBarColor = ResourcesCompat.getColor(resources,
            R.color.yellow_third, null)
        binding = FragmentLogOutBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        binding?.apply {
            logoutAuthButton.setOnClickListener {
                navigate(navCommandProvider.toAuthWithAuthState)
            }

            logoutRegButton.setOnClickListener {
                navigate(navCommandProvider.toAuthWithRegState)
            }
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

}