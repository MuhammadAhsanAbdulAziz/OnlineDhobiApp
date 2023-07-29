package com.example.onlinedhobi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.onlinedhobi.databinding.FragmentLoginBinding
import com.example.onlinedhobi.model.UserRequest
import com.example.onlinedhobi.util.Helper
import com.example.onlinedhobi.util.NetworkResult
import com.example.onlinedhobi.util.UtilManager
import com.example.onlinedhobi.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val authViewModel by viewModels<AuthViewModel>()
    @Inject
    lateinit var utilManager: UtilManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.btnSignIn.setOnClickListener {
            val validationResult = validateUserInfo()
            if(validationResult.first){
                authViewModel.loginUser(getUserRequest())
            }
            else{
                binding.txtError.text = validationResult.second
            }

        }

        bindObersers()

    }

    private fun getUserRequest(): UserRequest {
        val emailAddress = binding.txtEmail.text.toString()
        val password = binding.txtPassword.text.toString()
        return UserRequest(emailAddress,password)
    }

    private fun validateUserInfo(): Pair<Boolean, String> {
        val userRequest = getUserRequest()
        return Helper.validateLoginCredentials(userRequest.Email,userRequest.Password,true)
    }

    private fun bindObersers() {
        authViewModel.userResponseLiveData.observe(viewLifecycleOwner){
            binding.progressBar.isVisible = false
            when (it) {
                is NetworkResult.Success -> {
                    utilManager.saveUser(it.data!!)
                    utilManager.saveToken(it.data.token)
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                }

                is NetworkResult.Error -> {
                    binding.txtError.text = it.error
                }

                is NetworkResult.Loading -> {
                    binding.progressBar.isVisible = true
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}