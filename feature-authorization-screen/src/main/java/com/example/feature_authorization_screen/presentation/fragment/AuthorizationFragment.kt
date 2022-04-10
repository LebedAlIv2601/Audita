package com.example.feature_authorization_screen.presentation.fragment


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.feature_authorization_screen.R
import com.example.feature_authorization_screen.databinding.FragmentAuthorizationBinding
import com.example.feature_authorization_screen.di.AuthorizationComponentViewModel
import com.example.feature_authorization_screen.presentation.vm.AuthorizationViewModel
import com.example.feature_authorization_screen.presentation.vm.AuthorizationViewModelFactory
import javax.inject.Inject

class AuthorizationFragment : Fragment() {

    private var binding: FragmentAuthorizationBinding? = null

    @Inject
    internal lateinit var authorizationViewModelFactory: AuthorizationViewModelFactory

    private val vm: AuthorizationViewModel by viewModels{
        authorizationViewModelFactory
    }

    override fun onAttach(context: Context) {
        ViewModelProvider(this).get(AuthorizationComponentViewModel::class.java)
            .authorizationComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_authorization,
            container, false)


        if(vm.checkUserAuth()){
            Toast.makeText(this.context, "User already auth", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this.context, "User not auth", Toast.LENGTH_LONG).show()
        }

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setupObservers()

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupObservers() {
        vm.checkUserAuth()
    }
}