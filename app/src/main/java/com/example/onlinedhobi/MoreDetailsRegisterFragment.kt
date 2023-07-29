package com.example.onlinedhobi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.onlinedhobi.databinding.FragmentMoreDetailsRegisterBinding
import com.example.onlinedhobi.model.User
import com.example.onlinedhobi.model.UserRequest
import com.example.onlinedhobi.util.Helper
import com.example.onlinedhobi.util.NetworkResult
import com.example.onlinedhobi.viewmodel.AuthViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class MoreDetailsRegisterFragment : Fragment() {

    private var _binding: FragmentMoreDetailsRegisterBinding? = null
    private val binding get() = _binding!!
    private val authViewModel by viewModels<AuthViewModel>()
    private lateinit var fname:String
    private lateinit var lname:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoreDetailsRegisterBinding.inflate(inflater, container, false)
        setInitialData()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backbtn.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnSignUp.setOnClickListener {
            val validationResult = validateUserInfo()
            if(validationResult.first){
                authViewModel.registerUser(getUserRequest())
            }
            else{
                binding.txtError.text = validationResult.second
            }

        }
        bindObersers()
    }

    private fun setInitialData() {
        val name1 = arguments?.getString("fname")
        val name2 = arguments?.getString("lname")
        if (name1 != null && name2 != null) {
            fname = name1
            lname = name2
        }
    }

    private fun getUserRequest(): User {
        val cnic = binding.cnic.text.toString()
        val email = binding.email.text.toString()
        val phone = binding.phone.text.toString()
        val password = binding.password.text.toString()
        return User(cnic,email,fname,lname,password,phone,2)
    }

    private fun validateUserInfo(): Pair<Boolean, String> {
        val userRequest = getUserRequest()
        return Helper.validateRegisterCredentials(userRequest.Email,userRequest.CNIC,userRequest.Phone,
            userRequest.Password,binding.cpassword.text.toString())
    }

    private fun bindObersers() {
        authViewModel.userResponseLiveData.observe(viewLifecycleOwner){
            binding.progressBar.isVisible = false
            when (it) {
                is NetworkResult.Success -> {
                    successMessage()
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

    private fun successMessage() {
        val alertDialog = SweetAlertDialog(
            requireContext(),
            SweetAlertDialog.SUCCESS_TYPE
        ).setTitleText("IMPORTANT").setContentText("Acount Created Successfully").setConfirmText("Okay")
            .setConfirmClickListener { sweetAlertDialog ->
                findNavController().popBackStack()
                findNavController().popBackStack()
                sweetAlertDialog.cancel()
                sweetAlertDialog.dismiss()
            }
        alertDialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}