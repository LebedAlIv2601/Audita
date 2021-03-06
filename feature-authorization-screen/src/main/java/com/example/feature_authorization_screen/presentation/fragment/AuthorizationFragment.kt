package com.example.feature_authorization_screen.presentation.fragment


import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.core.Constants
import com.example.core.navigate
import com.example.feature_authorization_screen.R
import com.example.feature_authorization_screen.databinding.FragmentAuthorizationBinding
import com.example.feature_authorization_screen.di.AuthorizationComponentViewModel
import com.example.feature_authorization_screen.domain.model.UserForAuth
import com.example.feature_authorization_screen.navigation.AuthNavCommandProvider
import com.example.feature_authorization_screen.presentation.vm.AuthorizationViewModel
import com.example.feature_authorization_screen.presentation.vm.AuthorizationViewModelFactory
import com.example.feature_authorization_screen.utils.AuthScreenState
import com.example.feature_authorization_screen.utils.AuthorizationState
import javax.inject.Inject

class AuthorizationFragment : Fragment() {

    private var binding: FragmentAuthorizationBinding? = null

    @Inject
    internal lateinit var authorizationViewModelFactory: AuthorizationViewModelFactory

    @Inject
    internal lateinit var authNavCommandProvider: AuthNavCommandProvider

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

        activity?.window?.statusBarColor = ResourcesCompat.getColor(resources,
            R.color.yellow_second, null)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_authorization,
            container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        if(arguments?.getInt("state") == Constants.AUTH_SCREEN_REG_STATE){
            vm.changeAuthScreenState()
        }

        setupListeners()
        setupObservers()

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupListeners() {
        binding?.apply {

            buttonRegOrAuthUser.setOnClickListener {
                if(checkPassword() && checkNickname()) {
                    val user = UserForAuth(
                        nickname = nicknameEditText.text.toString(),
                        email = emailEditText.text.toString(),
                        password = passwordEditText.text.toString()
                    )
                    if(repeatPasswordCardView.isVisible){
                        regUser(user)
                    } else {
                        Log.e("callAuth", "Auth is calling")
                        authUser(user)
                    }
                }
            }

        }
    }

    private fun checkRegOrAuthState(state: AuthorizationState<Boolean>){
        when (state) {
            is AuthorizationState.Success -> {
                if(vm.checkUserAuth()){
                    Log.e("AuthorizeState", "Success")
                    binding?.authProgressBar?.visibility = View.GONE
                    Toast.makeText(this@AuthorizationFragment.context,
                        "Successfully authorized",
                        Toast.LENGTH_SHORT).show()
                    navigate(authNavCommandProvider.toMain)
                } else {
                    binding?.authProgressBar?.visibility = View.GONE
                    Toast.makeText(this@AuthorizationFragment.context,
                        "Not authorized",
                        Toast.LENGTH_SHORT).show()
                }
            }
            is AuthorizationState.Loading -> {
                Log.e("AuthorizeState", "Loading")
                binding?.authProgressBar?.visibility = View.VISIBLE
            }
            is AuthorizationState.Error -> {
                Log.e("AuthorizeState", "Error")
                binding?.authProgressBar?.visibility = View.GONE
                Toast.makeText(this@AuthorizationFragment.context,
                    state.message,
                    Toast.LENGTH_SHORT).show()
            }
            is AuthorizationState.Waiting ->{
                Log.e("AuthorizeState", "Waiting")
                binding?.authProgressBar?.visibility = View.GONE
            }
        }
    }

    private fun authUser(user: UserForAuth) {
        vm.authUser(user).observe(viewLifecycleOwner, Observer { state ->
            checkRegOrAuthState(state)
        })
    }

    private fun regUser(user: UserForAuth){
        vm.regUser(user).observe(viewLifecycleOwner, Observer { state ->
            checkRegOrAuthState(state)
        })
    }

    private fun checkNickname(): Boolean{
        binding?.apply {
            if(nicknameCardView.isVisible) {
                return if (nicknameEditText.text.toString().isEmpty()){
                    Toast.makeText(
                        this@AuthorizationFragment.context,
                        resources.getString(R.string.nickname_check_empty_toast_text),
                        Toast.LENGTH_SHORT
                    ).show()
                    false
                } else{
                    true
                }
            } else return true
        }
        return false
    }

    private fun checkPassword(): Boolean {
        binding?.apply {
            if(repeatPasswordCardView.isVisible) {
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
            } else if (passwordEditText.text.toString().length >= 8) {
                return true
            } else {
                Toast.makeText(
                    this@AuthorizationFragment.context,
                    resources.getString(R.string.password_check_length_toast_text),
                    Toast.LENGTH_SHORT
                ).show()
                return false
            }
        }
        return false
    }

    private fun setupObservers() {

        vm.authScreenState.observe(viewLifecycleOwner, Observer { state ->
            when (state){
                AuthScreenState.SIGN_IN -> {
                    binding?.apply {
                        nicknameCardView.visibility = View.GONE
                        repeatPasswordCardView.visibility = View.GONE
                        passwordEditText.imeOptions = EditorInfo.IME_ACTION_DONE
                        buttonRegOrAuthUser.setCardBackgroundColor(ResourcesCompat.getColor(resources,
                            R.color.black, null))
                        buttonRegOrAuthUserTextView.setTextColor(ResourcesCompat.getColor(resources,
                            R.color.yellow_second, null))
                        buttonRegOrAuthUserTextView.text = getString(R.string.button_next_text)
                    }
                }
                AuthScreenState.SIGN_UP -> {
                    binding?.apply {
                        nicknameCardView.visibility = View.VISIBLE
                        repeatPasswordCardView.visibility = View.VISIBLE
                        passwordEditText.imeOptions = EditorInfo.IME_ACTION_NEXT
                        buttonRegOrAuthUser.setCardBackgroundColor(ResourcesCompat.getColor(resources,
                            R.color.yellow_second, null))
                        buttonRegOrAuthUserTextView.setTextColor(ResourcesCompat.getColor(resources,
                            R.color.black, null))
                        buttonRegOrAuthUserTextView.text = getString(R.string.reg_button_text)
                    }
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}