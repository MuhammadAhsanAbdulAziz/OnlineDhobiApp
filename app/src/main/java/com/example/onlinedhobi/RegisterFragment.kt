package com.example.onlinedhobi

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.onlinedhobi.databinding.FragmentRegisterBinding
import com.example.onlinedhobi.model.UserRequest
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backbtn.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnLogin.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnSignUp.setOnClickListener {
            val validationResult = validateUserInfo()
            if(validationResult.first){
                val bundle = Bundle()
                bundle.putString("fname",binding.fname.text.toString())
                bundle.putString("lname",binding.lname.text.toString())
                findNavController().navigate(R.id.action_registerFragment_to_moreDetailsRegisterFragment,bundle)
            }
            else{
                binding.txtError.text = validationResult.second
            }

        }
    }
    private fun validateUserInfo(): Pair<Boolean, String> {
        val fname = binding.fname.text.toString()
        val lname = binding.lname.text.toString()
        var result = Pair(true,"")
        if(TextUtils.isEmpty(fname) || TextUtils.isEmpty(lname))
        {
            result = Pair(false,"Please provide the credentials")
        }
        return result
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}