package com.example.feature_authoriztion_screen.presentation.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.feature_authoriztion_screen.R
import com.example.feature_authoriztion_screen.databinding.FragmentAuthorizationBinding

class AuthorizationFragment : Fragment() {

    private var binding: FragmentAuthorizationBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_authorization,
            container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setupObservers()

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupObservers() {

    }
}