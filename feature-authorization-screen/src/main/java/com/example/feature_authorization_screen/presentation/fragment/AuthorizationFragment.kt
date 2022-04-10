package com.example.feature_authorization_screen.presentation.fragment


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.feature_authorization_screen.R
import com.example.feature_authorization_screen.databinding.FragmentAuthorizationBinding
import com.example.feature_authorization_screen.di.AuthorizationComponentViewModel
import com.example.feature_authorization_screen.domain.model.UserForAuth
import com.example.feature_authorization_screen.presentation.vm.AuthorizationViewModel
import com.example.feature_authorization_screen.presentation.vm.AuthorizationViewModelFactory
import com.example.feature_authorization_screen.utils.AuthScreenState
import com.example.feature_authorization_screen.utils.AuthorizationState
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

        setupListeners()
        setupObservers()

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupListeners() {
        binding?.apply {

            signInUpButton.setOnClickListener {
                vm.changeAuthScreenState()
            }

            authButton.setOnClickListener {
                if(checkPassword()) {
                    val user = UserForAuth(
                        nickname = nicknameEditText.text.toString(),
                        email = emailEditText.text.toString(),
                        password = passwordEditText.text.toString()
                    )
                    vm.authUser(user)
                }
            }

        }
    }

    private fun checkPassword(): Boolean {
        binding?.apply {
            if(repeatPasswordEditText.isVisible) {
                return if (passwordEditText.text.toString() == repeatPasswordEditText.text.toString()) {
                    if (passwordEditText.text.toString().length >= 8) {
                        true
                    } else {
                        Toast.makeText(
                            this@AuthorizationFragment.context,
                            resources.getString(R.string.password_check_length_toast_text),
                            Toast.LENGTH_SHORT
                        ).show()
                        false
                    }
                } else {
                    Toast.makeText(
                        this@AuthorizationFragment.context,
                        resources.getString(R.string.password_check_repeat_toast_text),
                        Toast.LENGTH_SHORT
                    ).show()
                    false
                }
            } else return true
        }
        return false
    }

    private fun setupObservers() {

        vm.authScreenState.observe(viewLifecycleOwner, Observer { state ->
            when (state){
                AuthScreenState.SIGN_IN -> {
                    binding?.apply {
                        nicknameEditText.visibility = View.GONE
                        nicknameLabelTextView.visibility = View.GONE
                        repeatPasswordEditText.visibility = View.GONE
                        repeatPasswordLabelTextView.visibility = View.GONE
                        authButton.text = resources.getString(R.string.auth_button_text)
                        signInUpButton.text = resources.getString(R.string.sign_up_button_text)
                    }
                }
                AuthScreenState.SIGN_UP -> {
                    binding?.apply {
                        nicknameEditText.visibility = View.VISIBLE
                        nicknameLabelTextView.visibility = View.VISIBLE
                        repeatPasswordEditText.visibility = View.VISIBLE
                        repeatPasswordLabelTextView.visibility = View.VISIBLE
                        authButton.text = resources.getString(R.string.registr_button_text)
                        signInUpButton.text = resources.getString(R.string.sign_in_button_text)
                    }
                }
            }
        })

        vm.isAuthDone.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is AuthorizationState.Success -> {
                    Log.e("AuthorizeState", "Success")
                    binding?.authProgressBar?.visibility = View.GONE
                    Toast.makeText(this@AuthorizationFragment.context,
                        "Successfully authorized",
                        Toast.LENGTH_SHORT).show()
                }
                is AuthorizationState.Loading -> {
                    Log.e("AuthorizeState", "Loading")
                    binding?.authProgressBar?.visibility = View.VISIBLE
                }
                is AuthorizationState.Error -> {
                    Log.e("AuthorizeState", "Error")
                    binding?.authProgressBar?.visibility = View.GONE
                    Toast.makeText(this@AuthorizationFragment.context,
                        "Authorize error",
                        Toast.LENGTH_SHORT).show()
                }
                is AuthorizationState.Waiting ->{
                    Log.e("AuthorizeState", "Waiting")
                    binding?.authProgressBar?.visibility = View.GONE
                }
            }
        })
    }
}